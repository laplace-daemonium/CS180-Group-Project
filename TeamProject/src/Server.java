import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This Server class implements server connection from the user. 
 * 
 * @author L04 - Team 06 
 * @version November, 2024
 */

public class Server implements Runnable {
    Socket socket;
    Database db = new Database();
    public Server(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        System.out.printf("connection received from %s\n", socket);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush(); // ensure data is sent to the client
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String s1 = "Hello!\nDo you have an account? (yes/no)";
            oos.writeObject(s1);
            oos.flush();
            String answer = (String) ois.readObject();

            User user;

            if (answer.equalsIgnoreCase("yes")) {
                oos.writeObject("Enter your username: ");
                oos.flush();
                String username = (String) ois.readObject();

                oos.writeObject("Enter your password: ");
                oos.flush();
                String password = Database.getHash((String) ois.readObject());
                if (!db.login(username, password)) {
                    oos.writeObject("Invalid username or password");
                    return;
                }

                user = new User(username, password);
               // oos.writeObject(user);
            } else if (answer.equalsIgnoreCase("no")) {

                String username;
                do {
                    oos.writeObject("Enter your new username: ");
                    oos.flush();
                    username = (String) ois.readObject();
                } while (Database.searchUsername(username));

                oos.writeObject("Enter your new password: ");
                oos.flush();
                String password = Database.getHash((String) ois.readObject());
                oos.writeObject("New user created successfully");
                user = new User();
                db.register(user, username, password);
            } else {
                return;
            }

            while (true) {
                int number = 0;
                String result = "Your list of chats: ";
                for (Chat chat : user.getMessagesData()) {
                    result += ++number + " " + chat.getUser2();;
                }
                oos.writeObject(result +"\nIf you want to add chat enter \"new\"");
                oos.flush();
                String s2 = (String) ois.readObject();
                if (s2.equals("new")) {
                    oos.writeObject("Enter friend name: ");
                    oos.flush();
                    String friend = (String) ois.readObject();
                    if (Database.searchUsername(friend)) {
                        user.getMessagesData().add(new Chat(user.getUsername(), friend));
                        user.saveMessagesData();
                    } else {
                        oos.writeObject("There is an error ");
                        oos.flush();
                    }
                } else if (Integer.parseInt(s2) < number) {
                    user.getMessagesData().get(Integer.parseInt(s2)).loadMessages();
                }


                if (s2.equals("END")) {
                    System.out.println("Server stopped");
                    break;
                }
            }
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4242);
        System.out.printf("socket open, waiting for connections on %s\n",
                serverSocket);
        while (true) {
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            new Thread(server).start();
        }
    }
}