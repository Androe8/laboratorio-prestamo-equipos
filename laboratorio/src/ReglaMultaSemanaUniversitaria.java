import java.util.List;
import equipos.Equipo;

/**
 * Clase que implementa la regla de multa para la semana universitaria.
 */
public class ReglaMultaSemanaUniversitaria implements ReglaMulta {
    @Override
    public double calcularMulta(List<Equipo> equipos, int diasRetraso) {
        ReglaMultaEstandar estandar = new ReglaMultaEstandar();
        return estandar.calcularMulta(equipos, diasRetraso) / 2.0;
    }
}


