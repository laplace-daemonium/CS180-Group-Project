import java.io.*;

public class Database implements DatabaseInterface {

    private static final Object lock = new Object();
    private static final String usersData = "userData.txt";

    // TODO: Password hashing
    public static String getHash(String word) {
        return word;
    }

    //Return true if user successfully registers
    public boolean register(User user, String username, String password) {
        synchronized (lock) {
            if (!(searchUsername(username))) {
                user.setUsername(username);
                user.setPassword(getHash(password));
                insertUser(user.getId(), username, getHash(password));
                return true;
            }
        }
        System.out.println("Already registered");
        return false;
    }

    //Returns true if user successfully logins
    public boolean login(String username, String password) {
        return (searchUsername(username) && matchPassword(username, getHash(password)));
    }

    //Returns if a specific username exists in 'userData.txt'
    public static boolean searchUsername(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersData));
            String line;
            String usernames;
            while((line = reader.readLine()) != null) {
                usernames = line.split(",")[1];
                if(usernames.equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }



    //
    public boolean matchPassword(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersData));
            String line;
            String usernames;
            String passwords;
            while((line = reader.readLine()) != null) {
                usernames = line.split(",")[1];
                passwords = line.split(",")[2];
                if(usernames.equals(username) && passwords.equals(password)) {
                    return true;
                }
            }

        } catch (IOException e) {
            return false;
        }
        return false;
    }

    //Inserts the users information into 'userData.txt'
    public void insertUser(int id, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersData, true))) {
            writer.write(String.format("%d,%s,%s", id, username, password));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Returns the line in 'userData.txt' that contains the specific username
    public String returnLine(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersData));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.split(",")[1].equals(username)) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
