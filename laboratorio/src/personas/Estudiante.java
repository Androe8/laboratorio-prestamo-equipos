package personas;

/**
 * Clase que representa a un estudiante.
 */

public class Estudiante extends Persona {
    private String programaAcademico;

    public Estudiante(String documento, String nombre, String programaAcademico) {
        super(documento, nombre);
        this.programaAcademico = programaAcademico;
    }

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    @Override //Este método sobrescribe el método abstracto de la clase Persona y devuelve el número máximo de días que un estudiante puede tener un préstamo.
    public int getDiasMaximosPrestamo() {
        return 3;
    }

    @Override
    public int getMaximoEquipos() {
        return 1;
    }
}