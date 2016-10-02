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
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incomingMessage;
    }
}
