package equipos;

/**
 * Clase que representa un proyector.
 */
public class Proyector extends Equipo {
    private int lumenes;

    public Proyector(String codigo, String marca, int lumenes) {
        super(codigo, marca);
        this.lumenes = lumenes;
    }

    public int getLumenes() {
        return lumenes;
    }

    @Override
    public double getMultaDiaria() {
        return 20000.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Lumenes: %d", lumenes);
    }
}
