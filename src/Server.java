import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private ArrayList<ClientHandler> clientHandlers;
    private ServerSocket server;
    private boolean done;

    public Server(){
        clientHandlers = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            while (!done) {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                clientHandlers.add(clientHandler);
            }
        }
        catch (IOException e) {
            try {
                shutdown();
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void broadcast(String message){
        for (ClientHandler clientHandler : clientHandlers){
            if (clientHandler != null){
                clientHandler.sendMessage(message);
            }
        }
    }

    public void shutdown() throws IOException {
        try {
            done = true;
            if (!server.isBound()){
                server.close();
        }
            for (ClientHandler clientHandler : clientHandlers){
                clientHandler.shutdown();
            }
        }
        catch (IOException e){
            //ignore
        }
    }
}
