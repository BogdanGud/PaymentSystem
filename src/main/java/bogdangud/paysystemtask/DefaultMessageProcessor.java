package bogdangud.paysystemtask;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DefaultMessageProcessor implements IMessageProcessor {
    DataStorage dataStorage = new DataStorage();
    private static TlsSender tlsSender = new TlsSender("payservicetest@gmail.com", "12345678test");

    @Override
    public void process(IncomingMessage incomingMessage) {
        System.out.println("ClientMessage processor works");
        if (incomingMessage.getDirection().equals("in")) {
            dataStorage.add(incomingMessage);
            System.out.println("Adding fields to DB started...");
        }

        if (incomingMessage.getDirection().equals("out")) {
            dataStorage.updateByLogin(incomingMessage);
            System.out.println("Updating fields to DB started...");
            System.out.println("Starting finding..");
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
        System.out.println("Time entry: ");
        System.out.println(entryTime);
        System.out.println("Check-out time: ");
        System.out.println(checkOutTime);
        String sendMessage = "Hi, client № " + clientId + "! Your amount for payment : "
                + sum + ", your entry time :" + entryTime + ", your check-out time :" + checkOutTime;
        tlsSender.send("Payment System", sendMessage, "payservicetest@gmail.com", email);
        System.out.println("Message send!!!");

    }

}
