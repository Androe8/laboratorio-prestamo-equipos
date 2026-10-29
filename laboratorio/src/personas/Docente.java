package personas;
 /**
 * Clase que representa un docente.
 */
public class Docente extends Persona {
    private String dependencia;

    public Docente(String documento, String nombre, String dependencia) {
        super(documento, nombre);
        this.dependencia = dependencia;
    }

    public String getDependencia() {
        return dependencia;
    }

    @Override //Este método sobrescribe el método abstracto de la clase Persona y devuelve el número máximo de días que un docente puede tener un préstamo.
    public int getDiasMaximosPrestamo() {
        return 10;
    }

    @Override
    public int getMaximoEquipos() {
        return 3;
    }
}

