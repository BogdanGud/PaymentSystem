package bogdangud.paysystemtask;


import com.mongodb.*;


import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataStorage {
    JsonParser parser = new JsonParser();

    // это клиент который обеспечит подключение к БД
    private MongoClient mongoClient;

    // В нашем случае, этот класс дает
// возможность аутентифицироваться в MongoDB
    private DB db;

    // тут мы будем хранить состояние подключения к БД
    private boolean authenticate;

    // И класс который обеспечит возможность работать
// с коллекциями / таблицами MongoDB
    private DBCollection table;

    public DataStorage() {
        try {
            // Создаем подключение
            mongoClient = new MongoClient("localhost", 27017);

            // Выбираем БД для дальнейшей работы
            db = mongoClient.getDB("paymentsystem");

            // Входим под созданным логином и паролем
            authenticate = db.authenticate("root", "root".toCharArray());

            // Выбираем коллекцию/таблицу для дальнейшей работы
            table = db.getCollection("users");
        } catch (UnknownHostException e) {
            // Если возникли проблемы при подключении сообщаем об этом
            System.err.println("Don't connect!");
        }
    }

    public void add(IncomingMessage incomingMessage) {
        BasicDBObject document = new BasicDBObject();

        document.put("clientId", incomingMessage.getClientId());
        document.put("direction", incomingMessage.getDirection());
        document.put("dateIn",  incomingMessage.getDate().getTime());
        document.put("dateOut", 0);
        document.put("priceIn", incomingMessage.getPrice());
        document.put("priceOut", 0);
        document.put("checkpointIdIn", incomingMessage.getCheckpointId());
        document.put("checkpointIdOut", 0);
        document.put("email", incomingMessage.getEmail());

        // записываем данные в коллекцию/таблицу
        table.insert(document);
    }

    public ClientMessage findMessage(IncomingMessage incomingMessage) {
        BasicDBObject query = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<>();
        obj.add(new BasicDBObject("clientId", incomingMessage.getClientId()));
        obj.add(new BasicDBObject("dateOut", incomingMessage.getDate().getTime()));
        query.put("$and", obj);

        DBObject result = table.findOne(query);
        //ClientMessage clientMessage = parser.parseDBtoMessage(result.toString());


        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClientId(Integer.valueOf(result.get("clientId").toString()));
        clientMessage.setDirection((result.get("direction").toString()));
        clientMessage.setDateIn(Long.valueOf(result.get("dateIn").toString()));
        clientMessage.setDateOut(Long.valueOf(result.get("dateOut").toString()));
        clientMessage.setPriceIn(Integer.valueOf(result.get("priceIn").toString()));
        clientMessage.setPriceOut(Integer.valueOf(result.get("priceOut").toString()));
        clientMessage.setCheckpointIdIn(Integer.valueOf(result.get("checkpointIdIn").toString()));
        clientMessage.setCheckpointIdOut(Integer.valueOf(result.get("checkpointIdOut").toString()));
        clientMessage.setEmail(result.get("email").toString());

        return clientMessage;
    }

    // login - это старый логин пользователя
// newLogin - это новый логин который мы хотим задать
    public void updateByLogin(IncomingMessage incomingMessage) {

        BasicDBObject newData = new BasicDBObject();

        newData.append("$set", new BasicDBObject()
                .append("direction", incomingMessage.getDirection())
                .append("dateOut", incomingMessage.getDate().getTime())
                .append("priceOut", incomingMessage.getPrice())
                .append("checkpointIdOut", incomingMessage.getCheckpointId()));

        BasicDBObject searchQuery = new BasicDBObject()
                .append("clientId", incomingMessage.getClientId())
                .append("dateOut", 0);

        table.update(searchQuery, newData);
    }

    public void deleteByLogin(String login) {
        BasicDBObject query = new BasicDBObject();

        // указываем какую запись будем удалять с коллекции
        // задав поле и его текущее значение
        query.put("login", login);

        // удаляем запись с коллекции/таблицы
        table.remove(query);
    }

}
