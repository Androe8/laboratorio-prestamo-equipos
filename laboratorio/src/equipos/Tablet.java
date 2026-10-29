package equipos;

/**
 * Clase que representa una tablet.
 */
public class Tablet extends Equipo {

    public Tablet(String codigo, String marca) {
        super(codigo, marca);
    }

    @Override
    public double getMultaDiaria() {
        return 8000.0;
    }
}
