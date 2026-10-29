import java.util.List;
import equipos.Equipo;

/**
 * Clase que implementa la regla de multa estándar para calcular la multa por retraso en la devolución de equipos.
 */

public class ReglaMultaEstandar implements ReglaMulta {
    @Override
    public double calcularMulta(List<Equipo> equipos, int diasRetraso) {
        if (diasRetraso <= 0) return 0.0; // No hay multa si no hay retraso
        double sumaMultaDiaria = 0.0; // Suma de las multas diarias de todos los equipos
        
        //Bucle for para recorrer la lista de equipos y sumar sus multas diarias
        for (Equipo equipo : equipos) {
            sumaMultaDiaria += equipo.getMultaDiaria();
        }
        return sumaMultaDiaria * diasRetraso; // Calcula la multa total multiplicando la suma de las multas diarias por los días de retraso
    }
}