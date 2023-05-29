import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    @Override
    public void run() {
        try {
            Socket client = new Socket("127.0.0.1", 8787);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch (IOException e){

        }
    }
    public void shutdown(){
        done = true;
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
