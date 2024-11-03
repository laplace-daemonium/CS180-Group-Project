public class Notification {
    private boolean silent;
    private String message;
    private String time;
    private User sender;
    private User receiver;

    public Notification(boolean silent, String message, String time, User sender, User receiver) {
        this.silent = silent;
        this.message = message;
        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
    }

}
