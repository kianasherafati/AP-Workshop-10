import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputHandler implements Runnable {
    private boolean done;

    @Override
    public void run() {
        Client client = new Client();
        try {
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            while (!done) {
                String message = inReader.readLine();
                if (message.equals("#exit")){
                    inReader.close();
                    client.shutdown();
                 }
                else {
                    client.getOut().println(message);
                }
            }
        }
        catch (IOException e) {
            client.shutdown();
        }
    }
}

