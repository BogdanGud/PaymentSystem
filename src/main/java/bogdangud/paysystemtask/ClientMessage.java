package bogdangud.paysystemtask;


import java.util.Date;

public class ClientMessage {
    public int clientId;
    public String direction;
    public long dateIn;
    public long dateOut;
    public double priceIn;
    public double priceOut;
    public int checkpointIdIn;
    public int checkpointIdOut;
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

    public long getDateIn() {
        return dateIn;
    }

    public void setDateIn(long dateIn) {
        this.dateIn = dateIn;
    }

    public long getDateOut() {
        return dateOut;
    }

    public void setDateOut(long dateOut) {
        this.dateOut = dateOut;
    }

    public double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(double priceIn) {
        this.priceIn = priceIn;
    }

    public double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(double priceOut) {
        this.priceOut = priceOut;
    }

    public int getCheckpointIdIn() {
        return checkpointIdIn;
    }

    public void setCheckpointIdIn(int checkpointIdIn) {
        this.checkpointIdIn = checkpointIdIn;
    }

    public int getCheckpointIdOut() {
        return checkpointIdOut;
    }

    public void setCheckpointIdOut(int checkpointIdOut) {
        this.checkpointIdOut = checkpointIdOut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "It is printing by ClientMessage{" +
                "clientId=" + clientId +
                ", direction='" + direction + '\'' +
                ", dateIn=" + dateIn +
               ", dateOut=" + dateOut +
                ", priceIn=" + priceIn +
                ", priceOut=" + priceOut +
                ", checkpointIdIn=" + checkpointIdIn +
                ", checkpointIdOut=" + checkpointIdOut +
                ", email='" + email + '\'' +
                '}';
    }
}
