import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Chat {
    private ArrayList<Message> messages;
    private String user1;
    private String user2;
    private String filename;

    // Creates a new chat; creates a new file with messages
    public Chat(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
        messages = new ArrayList<>();
        if (user1.compareTo(user2) < 0) {
            filename = user1 + "-" + user2 + ".txt";
        } else {
            filename = user2 + "-" + user1 + ".txt";
        }
    }

    // Shows an existing chat (we do not need to create new file with messages)
    public Chat(String user1, String user2, String filename) {
        this.user1 = user1;
        this.user2 = user2;
        this.filename = filename;
        messages = new ArrayList<>();
    }

    // loads messages from the file
    public void loadMessages() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            messages = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                messages.add(new Message(line.split(" ")[0], line.split(" ")[1], line.split(" ")[2]));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add a new message (user sends a message)
    public void addMessage(String sender, String message, String filename) {
        Date date = new Date(); // current time
        messages.add(new Message(sender, message, date.toString().split(" ")[3]));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(sender + " " + message + " " + date.toString().split(" ")[3]);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO notification
    }

    public void deleteMessage(String sender, String messageText, String filename) {
         messages.remove(new Message(sender, messageText, filename));

         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
             for (Message message : messages) {
                 writer.write(message.getSenderUsername() + " " + message.getMessage() + " " + message.getDate());
                 writer.newLine();
             }
         } catch (IOException e) {
             e.printStackTrace();
         }

         // TODO notification
    }

    public String getUser1() { return user1; }
    public String getUser2() { return user2; }
    public ArrayList<Message> getMessages() { return messages; }

}
