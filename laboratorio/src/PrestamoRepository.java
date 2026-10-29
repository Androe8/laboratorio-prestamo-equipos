import personas.Persona;
import java.util.List;

/**
 * Interfaz para el repositorio de préstamos.
 * La interfaz ayuda a definir las operaciones que se pueden realizar sobre los préstamos, como guardar un nuevo préstamo, obtener los préstamos activos de una persona y obtener todos los préstamos.
 * Esto permite que la implementación del repositorio pueda cambiar (por ejemplo, de una base de datos a otra) sin afectar al resto del código que utiliza esta interfaz.
 */

public interface PrestamoRepository {
    void guardar(Prestamo prestamo);
    List<Prestamo> obtenerActivosPorPersona(Persona persona);
    List<Prestamo> obtenerTodos();
}
