package equipos;

/**
 * Clase que representa un cargador.
 */
public class Cargador {
    private String serial;
    private int vatios;

    Cargador(String serial, int vatios) {
        this.serial = serial;
        this.vatios = vatios;
    }

    public String getSerial() {
        return serial;
    }

    public int getVatios() {
        return vatios;
    }

    @Override
    public String toString() {
        return String.format("[Serial: %s, Vatios: %dW]", serial, vatios);
    }
}
