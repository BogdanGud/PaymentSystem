package bogdangud.paysystemtask;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DefaultMessageProcessor implements IMessageProcessor {
    DataStorage dataStorage = new DataStorage();

    @Override
    public void process(IncomingMessage incomingMessage) {
        if (incomingMessage.getDirection().equals("in")) {
            dataStorage.add(incomingMessage);
        }

        if (incomingMessage.getDirection().equals("out")) {
            dataStorage.updateByLogin(incomingMessage);
            ClientMessage clientDBMessage = dataStorage.findMessage(incomingMessage);
            calculateAndSend(clientDBMessage);
        }
    }

    public void calculateAndSend(ClientMessage clientMessage) {
        String email = clientMessage.getEmail();
        int clientId = clientMessage.clientId;
        double sum = clientMessage.priceIn + clientMessage.priceOut;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String entryTime = sdf.format(new Date(clientMessage.dateIn));
        String checkOutTime = sdf.format(new Date(clientMessage.dateOut));
        String sendMessage = "Hi, client № " + clientId + "! Your amount for payment : "
                + sum + ", your entry time :" + entryTime + ", your check-out time :" + checkOutTime;
        new Thread(() -> {
            MailSender mailSender = new MailSender("payservicetest@gmail.com", "12345678test");
            mailSender.send("Payment System", sendMessage, "payservicetest@gmail.com", email);
        }).start();
    }
}
