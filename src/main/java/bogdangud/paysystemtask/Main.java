package bogdangud.paysystemtask;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;

public class Main {


    public static void main(String[] args)  {

        SocketService listener = new SocketService();
        listener.startListener(9898);

    }

}




