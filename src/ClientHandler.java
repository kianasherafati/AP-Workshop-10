import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String name;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Server server = new Server();
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println("please enter your name:");
            name = in.readLine();
            System.out.println(name + "connected!");
            server.broadcast(name + " joined Chatroom!");
            String message;
            while ((message = in.readLine()) != null){
                if (message.equals("#exit")){
                    server.broadcast(name + " left the chat!");
                    shutdown();
                }
                else {
                    server.broadcast(name + ": " + message);
                }
            }

        }
        catch (IOException e) {
            shutdown();
        }
    }

    public void sendMessage(String message){
        out.println(message);
    }

    public void shutdown() {
        try {
            in.close();
            out.close();
            if (!client.isClosed()){
                client.close();
            }
        }
        catch (IOException e){
            //ignore
        }
    }

}
