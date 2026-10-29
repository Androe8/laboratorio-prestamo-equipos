import personas.Persona;

 /**
 * Clase que implementa el notificador de servicios para la consola.
 */
public class NotificadorConsola implements NotificadorServicio {
    @Override
    public void enviarAviso(Persona persona, String mensaje) {
        System.out.println("  [AVISO CONSOLA -> " + persona.getNombre() + " (" + persona.getDocumento() + ")]: " + mensaje);
    }
} 

