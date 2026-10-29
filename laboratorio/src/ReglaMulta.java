import java.util.List;
import equipos.Equipo;

/**
 * Interfaz que define la regla para calcular la multa por retraso en la devolución de equipos.
 */

public interface ReglaMulta {
    double calcularMulta(List<Equipo> equipos, int diasRetraso);
} 
