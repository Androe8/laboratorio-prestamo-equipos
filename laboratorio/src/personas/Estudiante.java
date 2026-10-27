package personas;

public class Estudiante extends Persona {
    private String codigoEstudiantil;
    private String carrera;

    public Estudiante(String documentoIdentidad, String nombre, String email, String codigoEstudiantil, String carrera) {
        super(documentoIdentidad, nombre, email); // Llama al constructor de Persona
        this.codigoEstudiantil = codigoEstudiantil;
        this.carrera = carrera;
    }

    @Override
    public String obtenerRol() {
        return "Estudiante";
    }

    

    // Getters y Setters propios
    public String getCodigoEstudiantil() { return codigoEstudiantil; }
    public String getCarrera() { return carrera; }
}
