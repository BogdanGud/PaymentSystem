package bogdangud.paysystemtask;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService extends Thread {
    ServerSocket serverSocket;
    JsonParser parser = new JsonParser();
    DefaultMessageProcessor messageProcessor = new DefaultMessageProcessor();
    IncomingMessage incomingMessage = new IncomingMessage();


    public void startListener(int portNumber) {
        try {
            System.out.println("The socket-listener is running");
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket incoming = serverSocket.accept();
                Thread t = new Thread(new MyListener(incoming));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class MyListener implements Runnable {
        Socket socket;

        public MyListener(Socket socket) {
            this.socket = socket;
            log("New connection at " + socket);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    String input = in.readLine();
                    if (input == null ) {
                        break;
                    }
                    log("Client incomingMessage:" + input);
                    incomingMessage = parser.run(input);
                    messageProcessor.process(incomingMessage);
                }
            } catch (IOException e) {
                log("Error handling client: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e1) {
                    log("Couldn't close a socket" + e1);
                }
                log("Connection closed");
            }
        }

        private void log(String message) {
            System.out.println(message);
        }
    }
}
