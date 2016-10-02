package bogdangud.paysystemtask;


public class Main {

    public static void main(String[] args)  {
        SocketService listener = new SocketService();
        listener.startListener(9898);
    }
}




