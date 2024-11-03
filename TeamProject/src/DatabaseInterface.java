public interface DatabaseInterface {
    boolean register(User user, String username, String password);
    boolean login(String username, String password);
    //static boolean searchUsername(String username);
    boolean matchPassword(String username, String password);
    void insertUser(int id, String username, String password);
    String returnLine(String username);

}
