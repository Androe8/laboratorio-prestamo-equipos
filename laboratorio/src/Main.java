import java.util.Arrays;
import java.util.List;
import personas.Docente;
import personas.Estudiante;
import equipos.Equipo;
import equipos.Portatil;
import equipos.Proyector;
import equipos.Tablet;


public class Main {
    public static void main(String[] args) {
        System.out.println("=================== Laboratorio Prestamo Equipos ===================\n");

        // 1. Crear dos portátiles, un proyector, una tablet, una estudiante y un docente
        Portatil laptop1 = new Portatil("LAP01", "Dell", 16, "CHG-D16", 65);
        Portatil laptop2 = new Portatil("LAP02", "Lenovo", 8, "CHG-L08", 45);
        Proyector proyector = new Proyector("PRY01", "Epson", 3500);
        Tablet tablet = new Tablet("TAB01", "Samsung");

        List<Equipo> inventario = Arrays.asList(laptop1, laptop2, proyector, tablet);

        Estudiante estudiante = new Estudiante("100123456", "Luisa Lopez", "Ingeniería de Software");
        Docente docente = new Docente("709876543", "Luis Fernando", "Facultad de Ingeniería");

        PrestamoRepository repo = new PrestamoRepositoryMemoria();
        NotificadorServicio notificador = new NotificadorConsola();
        ReglaMulta reglaEstandar = new ReglaMultaEstandar();

        ServicioPrestamos servicio = new ServicioPrestamos(repo, notificador, reglaEstandar);

        double acumuladoMultas = 0.0;

        // 2. La estudiante presta un portátil. El docente presta el proyector y la tablet.
        System.out.println("---> 2. Préstamos iniciales válidos:");
        Prestamo pEstudiante = servicio.prestar(estudiante, laptop1);
        Prestamo pDocente = servicio.prestar(docente, Arrays.asList(proyector, tablet));

        // 3. Intentos de préstamos rechazados
        System.out.println("\n---> 3. Pruebas de rechazo:");
        try {
            System.out.println("Intentando que la estudiante preste otro portátil (debe fallar por límite)...");
            servicio.prestar(estudiante, laptop2);
        } catch (Exception e) {
            System.out.println("   RECHAZADO: " + e.getMessage());
        }

        try {
            System.out.println("Intentando que el docente preste el portátil ocupado LAP01...");
            servicio.prestar(docente, laptop1);
        } catch (Exception e) {
            System.out.println("   RECHAZADO: " + e.getMessage());
        }

        // 4. Imprimir el inventario
        System.out.println("\n---> 4. Estado del inventario tras préstamos:");
        ReporteInventario.mostrarInventario(inventario);

        // 5. Devoluciones
        System.out.println("---> 5. Devoluciones de equipos:");
        acumuladoMultas += servicio.devolver(pEstudiante, 5); // 2 días de retraso
        acumuladoMultas += servicio.devolver(pDocente, 8);    // A tiempo

        // 6. Prueba con Semana Universitaria usando otro servicio
        System.out.println("\n---> 6. Préstamo con tarifa de Semana Universitaria:");
        ServicioPrestamos servicioSemana = new ServicioPrestamos(
                repo, notificador, new ReglaMultaSemanaUniversitaria());
        
        Prestamo pSemana = servicioSemana.prestar(estudiante, laptop1);
        // Devolución a los 5 días (2 días retraso). Multa estándar = $30.000, con descuento = $15.000
        acumuladoMultas += servicioSemana.devolver(pSemana, 5);

        // 7. Total de multas e inventario final
        System.out.println("\n---> 7. Resultado Final:");
        System.out.println("TOTAL RECAUDADO EN MULTAS: $" + String.format("%.2f", acumuladoMultas)); //"%.2f" formatea el número a dos decimales
        ReporteInventario.mostrarInventario(inventario);
    }
}
