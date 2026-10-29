import java.util.Arrays;
import java.util.List;
import personas.Persona;
import equipos.Equipo;


public class ServicioPrestamos {
    private PrestamoRepository prestamoRepository;
    private NotificadorServicio notificador;
    private ReglaMulta reglaMulta;

    public ServicioPrestamos(PrestamoRepository prestamoRepository, NotificadorServicio notificador, ReglaMulta reglaMulta) {
        this.prestamoRepository = prestamoRepository;
        this.notificador = notificador;
        this.reglaMulta = reglaMulta;
    }

    public Prestamo prestar(Persona persona, Equipo equipo) {
        return prestar(persona, Arrays.asList(equipo));
    }

    public Prestamo prestar(Persona persona, List<Equipo> equipos) {
        if (equipos == null || equipos.isEmpty()) {
            throw new IllegalArgumentException("Un préstamo sin equipos no es válido.");
        }

        for (Equipo e : equipos) {
            if (!e.isDisponible()) {
                throw new IllegalStateException("El equipo " + e.getCodigo() + " (" + e.getMarca() + ") no está disponible.");
            }
        }

        List<Prestamo> prestamosActivos = prestamoRepository.obtenerActivosPorPersona(persona);
        int equiposActivos = 0;
        for (Prestamo p : prestamosActivos) {
            equiposActivos += p.getEquipos().size();
        }

        if (equiposActivos + equipos.size() > persona.getMaximoEquipos()) {
            throw new IllegalStateException("La persona " + persona.getNombre() + " excede el máximo permitido de " 
                    + persona.getMaximoEquipos() + " equipo(s).");
        }

        for (Equipo e : equipos) {
            e.setDisponible(false);
        }

        Prestamo nuevoPrestamo = new Prestamo(persona, equipos);
        prestamoRepository.guardar(nuevoPrestamo);

        notificador.enviarAviso(persona, "Préstamo #" + nuevoPrestamo.getNumeroConsecutivo() 
                + " aprobado con " + equipos.size() + " equipo(s). Días de préstamo: " + persona.getDiasMaximosPrestamo());

        return nuevoPrestamo;
    }

    public double devolver(Prestamo prestamo, int diasUsados) {
        if (!prestamo.isActivo()) {
            throw new IllegalStateException("El préstamo #" + prestamo.getNumeroConsecutivo() + " ya fue devuelto.");
        }

        for (Equipo e : prestamo.getEquipos()) {
            e.setDisponible(true);
        }
        prestamo.setActivo(false);

        int diasMaximos = prestamo.getPersona().getDiasMaximosPrestamo();
        int diasRetraso = Math.max(0, diasUsados - diasMaximos);

        double multaTotal = reglaMulta.calcularMulta(prestamo.getEquipos(), diasRetraso);

        notificador.enviarAviso(prestamo.getPersona(), "Devolución del préstamo #" + prestamo.getNumeroConsecutivo() 
                + " registrada. Días usados: " + diasUsados + " (Retraso: " + diasRetraso + " días). Multa: $" + String.format("%.2f", multaTotal));

        return multaTotal;
    }

    public void setReglaMulta(ReglaMulta reglaMulta) {
        this.reglaMulta = reglaMulta;
    }
}
