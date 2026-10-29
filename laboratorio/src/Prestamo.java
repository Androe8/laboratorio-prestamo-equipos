import personas.Persona;
import equipos.Equipo;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un préstamo de equipos a una persona.
 */
public class Prestamo {
    private static int contadorConsecutivo = 1; // Contador estático para asignar números consecutivos a los préstamos
    
    private final int numeroConsecutivo;
    private final Persona persona;
    private final List<Equipo> equipos;
    private boolean activo;

    public Prestamo(Persona persona, List<Equipo> equipos) {
        this.numeroConsecutivo = contadorConsecutivo++;
        this.persona = persona;
        this.equipos = new ArrayList<>(equipos);
        this.activo = true;
    }

    public int getNumeroConsecutivo() {
        return numeroConsecutivo;
    }

    public Persona getPersona() {
        return persona;
    }

    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    public boolean isActivo() {
        return activo;
    }

    void registrarDevolucion() {
        if (!activo) {
            throw new IllegalStateException("El préstamo #" + numeroConsecutivo + " ya fue devuelto.");
        }
        activo = false;
    }
}
