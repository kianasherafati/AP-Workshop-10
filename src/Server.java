import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private ArrayList<ClientHandler> clientHandlers;

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket client = server.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandlers.add(clientHandler);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
