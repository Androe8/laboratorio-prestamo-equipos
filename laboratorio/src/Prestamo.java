/**
 * Clase que representa un préstamo de equipos a una persona.
 */
import personas.Persona;
import equipos.Equipo;
import java.util.ArrayList;
import java.util.List;

public class Prestamo {
    private static int contadorConsecutivo = 1;
    
    private int numeroConsecutivo;
    private Persona persona;
    private List<Equipo> equipos;
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

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}


