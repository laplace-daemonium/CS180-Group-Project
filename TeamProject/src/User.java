import java.io.*;
import java.util.ArrayList;

public class User implements UserInterface {
    private static int nextId = 0; // change (when the program starts it becomes 0)
    private int id;
    private String username;
    private String password;
    private String file;
    private ArrayList<Chat> messagesData;
    private ArrayList<Notification> notificationsData;

// login
    public User(String username, String password) {
        this.username = username;
        this.password = Database.getHash(password);
        this.messagesData = new ArrayList<>();
    }

    // Register a new user
    public User() {
        synchronized (this) {
            this.id = nextId++;
        }
        file = "user" + this.id + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.write(String.format("%d", this.id));
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        messagesData = new ArrayList<>();
        notificationsData = new ArrayList<>();
    }

    public void loadMessagesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                messagesData.add(new Chat(this.username, line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters and setter
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public ArrayList<Chat> getMessagesData() { return messagesData; }

    public void saveMessagesData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Chat message : messagesData) {
                writer.write(this.username + " " + message.getUser2());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
