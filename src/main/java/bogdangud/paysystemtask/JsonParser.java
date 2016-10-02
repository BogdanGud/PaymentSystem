package bogdangud.paysystemtask;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    public IncomingMessage run(String input) {
        ObjectMapper mapper = new ObjectMapper();
        IncomingMessage incomingMessage = new IncomingMessage();
        try {
            incomingMessage = mapper.readValue(input, IncomingMessage.class);
            System.out.println(incomingMessage);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incomingMessage;
    }

    public ClientMessage parseDBtoMessage(String input) {
        ObjectMapper mapper = new ObjectMapper();
        ClientMessage clientMessage = new ClientMessage();
        try {
            clientMessage = mapper.readValue(input, ClientMessage.class);
            System.out.println(clientMessage);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientMessage;
    }
}
