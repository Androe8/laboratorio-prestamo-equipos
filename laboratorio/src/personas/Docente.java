package personas;

public class Docente extends Persona {
    private String departamento;
    private String especialidad;

    public Docente(String documentoIdentidad, String nombre, String email, String departamento, String especialidad) {
        super(documentoIdentidad, nombre, email);
        this.departamento = departamento;
        this.especialidad = especialidad;
    }

    @Override
    public String obtenerRol() {
        return "Docente";
    }

    // Comportamiento propio de un docente
    

    // Getters y Setters propios
    public String getDepartamento() { return departamento; }
    public String getEspecialidad() { return especialidad; }
}
