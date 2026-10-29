import personas.Persona;

/**
 * Interfaz que define el comportamiento para notificar servicios.
 */

public interface NotificadorServicio {
    void enviarAviso(Persona persona, String mensaje);
}


