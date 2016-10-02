package bogdangud.paysystemtask;


import java.util.Date;

public class IncomingMessage {
    public int clientId;
    public String direction;
    public Date date;
    public int checkpointId;
    public int price;
    public String email;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(int checkpointId) {
        this.checkpointId = checkpointId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "It is print by IncomingMessage{" +
                "clientId=" + clientId +
                ", direction='" + direction + '\'' +
                ", date=" + date +
                ", checkpointId=" + checkpointId +
                ", price=" + price +
                ", email='" + email + '\'' +
                '}';
    }
}
