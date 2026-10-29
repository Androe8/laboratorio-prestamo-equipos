import java.util.ArrayList;
import java.util.List;
import personas.Persona;

/* 
 * Implementación en memoria del repositorio de préstamos.
 *esta clase implementa la interfaz PrestamoRepository y almacena los préstamos en una lista en memoria.
 * */


public class PrestamoRepositoryMemoria implements PrestamoRepository {
    private List<Prestamo> prestamos = new ArrayList<>();

    @Override
    public void guardar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    @Override
    public List<Prestamo> obtenerActivosPorPersona(Persona persona) {
        List<Prestamo> activos = new ArrayList<>();
        // Recorre la lista de préstamos y agrega a la lista "activos" aquellos que estén activos y correspondan a la persona indicada
        for (Prestamo p : prestamos) {
            if (p.isActivo() && p.getPersona().getDocumento().equals(persona.getDocumento())) {
                activos.add(p);
            }
        }
        return activos;
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return new ArrayList<>(prestamos);
    }
}
