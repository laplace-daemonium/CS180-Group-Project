import java.util.Date;

public class Main implements Runnable {
    public void run() {

    }
    public static void main(String[] args) {
        User user = new User();// new user
        User user2 = new User();
        Database database = new Database();
        database.register(user, "wecds", "wsef");
        database.register(user2, "fewre", "vte");
        Date date = new Date();
        System.out.println(date.toString());
    }
}
