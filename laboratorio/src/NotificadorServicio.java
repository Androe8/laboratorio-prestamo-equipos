import personas.Persona;

/**
 * Interfaz que define el comportamiento para notificar servicios.
 * Sirve para enviar avisos a las personas que realizan préstamos,
 * permitiendo que diferentes implementaciones puedan manejar la
 * notificación de distintas maneras (por ejemplo, por consola, correo electrónico, etc.).
 */

public interface NotificadorServicio {
    void enviarAviso(Persona persona, String mensaje);
}
