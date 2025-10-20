/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro Escobar 20251020094
 * Sebastian Zambrano 20251020102
 */

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String user1;
    private String user2;
    private List<Message> messages;


    /**
     * This is the constructor of the chat with tho ussers (tutor and Student)
     * @param User1
     * @param User2
     */
    public Chat(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<>();
    }

    /**
     * Envía un mensaje en el chat
     * @param sender  name of who send the message
     * @param content text conntent of the menssage
     */
    public void sendMessage(String sender, String content) {
        Message newMessage = new Message(sender, content);
        messages.add(newMessage);
    }

    /**
     * show all history of the chat
     */
    public void showChatHistory() {
        if (messages.isEmpty()) {
            System.out.println("No messages yet.");
            return;
        }

        System.out.println("Chat between " + user1 + " and " + user2 + ":");
        for (Message msg : messages) {
            System.out.println(msg.toString());
        }
    }

    /**
     * Gets the total number of messages sent
     */
    public int getMessageCount() {
        return messages.size();
    }
}
