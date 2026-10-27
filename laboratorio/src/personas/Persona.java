package personas;

public abstract class Persona {
    private String documentoIdentidad;
    private String nombre;
    private String email;

    // Constructor
    public Persona(String documentoIdentidad, String nombre, String email) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.email = email;
    }

    // Método abstracto: obliga a cada subclase a definir cómo se presenta o muestra su rol
    public abstract String obtenerRol();

    // Getters y Setters (Encapsulamiento)
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Método común reutilizable
    public void mostrarInformacion() {
        System.out.println("[" + obtenerRol() + "] Documento de Identidad: " + documentoIdentidad + " | Nombre: " + nombre + " | Email: " + email);
    }
}
