// Equipo.java
package equipos;

/**
 * Clase abstracta que representa un equipo.
 */
public abstract class Equipo {
    private final String codigo; //final, para que no se pueda cambiar después de la creación del objeto
    private final String marca;
    private boolean disponible;

    public Equipo(String codigo, String marca) {
        this.codigo = codigo;
        this.marca = marca;
        this.disponible = true;
    }

    //Este método devuelve el código del equipo.
    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void marcarPrestado() {
        if (!disponible) {
            throw new IllegalStateException("El equipo " + codigo + " ya está prestado.");
        }
        disponible = false;
    }

    public void marcarDevuelto() {
        if (disponible) {
            throw new IllegalStateException("El equipo " + codigo + " ya está disponible.");
        }
        disponible = true;
    }

    public abstract double getMultaDiaria();

    @Override //Este método sobrescribe el método toString() de la clase Object y devuelve una representación en cadena del equipo, incluyendo su tipo, código, marca y estado de disponibilidad.
    public String toString() {
        return String.format("[%s] %s %s - Estado: %s", 
            getClass().getSimpleName(), codigo, marca, disponible ? "Disponible" : "Prestado");
    }
    //getClass() devuelve la clase del objeto.
    //getSimpleName() devuelve el nombre simple de la clase del objeto, que en este caso es el tipo de equipo (por ejemplo, "Portatil", "Proyector", etc.).
}