/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro Escobar 20251020094
 * Sebastian Zambrano 20251020102
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String user1;
    private String user2;
    private List<Message> messages;
    private File historyFile; // archivo donde se guarda el historial

    /**
     * Constructor del chat entre dos usuarios.
     * Si ya existe un historial, lo carga.
     */
    public Chat(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<>();

        // Nombre único para el archivo del chat (puedes ajustarlo si manejas IDs)
        this.historyFile = new File("chat_" + user1.replace(" ", "_") + "_" + user2.replace(" ", "_") + ".txt");

        loadChatHistory(); // cargar historial si existe
    }

    /**
     * Envía un mensaje y lo guarda en el archivo
     */
    public void sendMessage(String sender, String content) {
        Message newMessage = new Message(sender, content);
        messages.add(newMessage);
        saveMessageToFile(newMessage);
    }

    /**
     * Guarda un solo mensaje en el archivo (modo append)
     */
    private void saveMessageToFile(Message msg) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true))) {
            writer.write(msg.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    /**
     * Carga el historial desde el archivo si existe
     */
    private void loadChatHistory() {
        if (!historyFile.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // (opcional) muestra historial cargado
            }
        } catch (IOException e) {
            System.out.println("Error loading chat history: " + e.getMessage());
        }
    }

    /**
     * Muestra el historial completo de mensajes en consola
     */
    public void showChatHistory() {
        System.out.println("\n=== Chat between " + user1 + " and " + user2 + " ===");
        for (Message msg : messages) {
            System.out.println(msg);
        }
    }

    public int getMessageCount() {
        return messages.size();
    }
}
