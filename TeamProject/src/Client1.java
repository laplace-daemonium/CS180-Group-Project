import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) throws UnknownHostException, IOException,
            ClassNotFoundException {

        Socket socket = new Socket("localhost", 4242);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.flush(); // ensure data is sent to the server
        Scanner sc = new Scanner(System.in);
        while (socket.isConnected()) {
            String s1 = (String) ois.readObject();
            System.out.println(s1);
            String answer = sc.nextLine();
            oos.writeObject(answer);
            oos.flush();
        }

        oos.close();
        ois.close();
    }
}