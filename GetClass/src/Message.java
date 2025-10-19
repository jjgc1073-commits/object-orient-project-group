/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro Escobar 20251020094
 * Sebastian Zambrano 20251020102
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    /**
     * Constructor de Message
     * 
     * @param sender  autor del mensaje
     * @param content contenido del mensaje
     */
    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now(); // guarda fecha y hora actuales
    }

    // Getters
    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Devuelve una representación formateada del mensaje
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + timestamp.format(formatter) + "] " + sender + ": " + content;
    }
}
