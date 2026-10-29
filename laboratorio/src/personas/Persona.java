package personas;

/**
 * Clase abstracta que representa una persona.
 */

public abstract class Persona {
    private final String documento;
    private String nombre;

    public Persona(String documento, String nombre) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento es obligatorio y no puede cambiar.");
        }
        this.documento = documento;
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract int getDiasMaximosPrestamo();
    public abstract int getMaximoEquipos();
}