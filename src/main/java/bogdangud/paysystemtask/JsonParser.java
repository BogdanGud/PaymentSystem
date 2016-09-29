package bogdangud.paysystemtask;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    public void run(String input) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Message message = mapper.readValue(input, Message.class);
            System.out.println(message);
        } catch (JsonGenerationException e) {
        e.printStackTrace();
    } catch (JsonMappingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
