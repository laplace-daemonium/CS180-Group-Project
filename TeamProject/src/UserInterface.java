/**
 * Interface for User.
 * Manages id, username and password of the user. 
 * 
 * @author L04 - Team 06 
 * @version November, 2024
 */
public interface UserInterface {
    int getId();
    String getUsername();
    String getPassword();
    void setUsername(String username);
    void setPassword(String password);
}
