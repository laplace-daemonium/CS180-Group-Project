public class Message {

    private String message;
    private String date;
    private String senderUsername;
    //TODO photos

    public Message(String message, String date, String senderUsername) {
        this.message = message;
        this.date = date;
        this.senderUsername = senderUsername;
    }

    public String getMessage() { return message; }
    public String getDate() { return date; }
    public String getSenderUsername() { return senderUsername; }
    public void setMessage(String message) { this.message = message; }
    public void setDate(String date) { this.date = date; }
    public void setSenderUsername(String senderUsername) { this.senderUsername = senderUsername; }
}
