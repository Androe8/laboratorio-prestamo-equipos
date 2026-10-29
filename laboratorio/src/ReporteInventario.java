import java.util.List;
import equipos.Equipo;

public class ReporteInventario {
    public static void mostrarInventario(List<Equipo> equipos) {
        System.out.println("\n======= INVENTARIO DE EQUIPOS ===========");
        // Recorrido del inventario completo con un solo ciclo (Polimorfismo)
        for (Equipo e : equipos) {
            System.out.println(e);
        }
        System.out.println("===================\n");
    }
}
