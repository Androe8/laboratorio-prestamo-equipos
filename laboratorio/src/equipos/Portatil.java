package equipos;

/**
 * Clase que representa un portátil.
 */
public class Portatil extends Equipo {
    private int memoriaRamGb;
    private Cargador cargador;

    public Portatil(String codigo, String marca, int memoriaRamGb, String serialCargador, int vatiosCargador) {
        super(codigo, marca);
        this.memoriaRamGb = memoriaRamGb;
        // Composición: El cargador se instancia dentro del portátil
        this.cargador = new Cargador(serialCargador, vatiosCargador);
    }

    public int getMemoriaRamGb() {
        return memoriaRamGb;
    }

    public Cargador getCargador() {
        return cargador;
    }

    @Override
    public double getMultaDiaria() {
        return 15000.0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | RAM: %2d GB | Cargador: %s", memoriaRamGb, cargador);
    }
}