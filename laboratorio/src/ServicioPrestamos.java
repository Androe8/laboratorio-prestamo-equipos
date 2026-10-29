import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import personas.Persona;
import equipos.Equipo;

/**
 * Clase que representa el servicio de préstamos de equipos.
 * Esta clase maneja la lógica de negocio relacionada con los préstamos, incluyendo la validación de disponibilidad de equipos,
 * el control del número máximo de equipos por persona, y la notificación de eventos relevantes.
 */

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
        if (persona == null) {
            throw new IllegalArgumentException("La persona es obligatoria.");
        }
        if (equipos == null || equipos.isEmpty()) {
            throw new IllegalArgumentException("Un préstamo sin equipos no es válido.");
        }

        Set<Equipo> equiposUnicos = new HashSet<>();
        for (Equipo e : equipos) {
            if (e == null) {
                throw new IllegalArgumentException("La lista no puede contener equipos nulos.");
            }
            if (!equiposUnicos.add(e)) {
                throw new IllegalArgumentException("Un equipo no puede repetirse en el mismo préstamo.");
            }
            // Verifica si el equipo está disponible para préstamo
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
            e.marcarPrestado();
        }

        Prestamo nuevoPrestamo = new Prestamo(persona, equipos);
        prestamoRepository.guardar(nuevoPrestamo);

        notificador.enviarAviso(persona, "Préstamo #" + nuevoPrestamo.getNumeroConsecutivo() 
                + " aprobado con " + equipos.size() + " equipo(s). Días de préstamo: " + persona.getDiasMaximosPrestamo());

        return nuevoPrestamo;
    }

    public double devolver(Prestamo prestamo, int diasUsados) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo es obligatorio.");
        }
        if (diasUsados < 0) {
            throw new IllegalArgumentException("Los días usados no pueden ser negativos.");
        }
        if (!prestamo.isActivo()) {
            throw new IllegalStateException("El préstamo #" + prestamo.getNumeroConsecutivo() + " ya fue devuelto.");
        }

        int diasMaximos = prestamo.getPersona().getDiasMaximosPrestamo();
        int diasRetraso = Math.max(0, diasUsados - diasMaximos);
        double multaTotal = reglaMulta.calcularMulta(prestamo.getEquipos(), diasRetraso);

        for (Equipo e : prestamo.getEquipos()) {
            e.marcarDevuelto();
        }
        prestamo.registrarDevolucion();

        notificador.enviarAviso(prestamo.getPersona(), "Devolución del préstamo #" + prestamo.getNumeroConsecutivo() 
                + " registrada. Días usados: " + diasUsados + " (Retraso: " + diasRetraso + " días). Multa: $" + String.format("%.2f", multaTotal));

        return multaTotal;
    }

    public void setReglaMulta(ReglaMulta reglaMulta) {
        this.reglaMulta = reglaMulta;
    }
}
