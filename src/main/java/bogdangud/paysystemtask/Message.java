package bogdangud.paysystemtask;


import java.util.Date;

public class Message {
    public int clientId;
    public String direction;
    public Date date;
    public int checkpointId;
    public String email;

    @Override
    public String toString() {
        return "IT is printing by MessageClass{" +
                "clientId=" + clientId +
                ", direction='" + direction + '\'' +
                ", date=" + date +
                ", checkpointId=" + checkpointId +
                ", email='" + email + '\'' +
                '}';
    }
}
