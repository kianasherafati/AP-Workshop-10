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
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out.println("please enter your name:");
            name = in.readLine();
            System.out.println(name + "joined chatroom!");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){
        out.println(message);
    }
}
