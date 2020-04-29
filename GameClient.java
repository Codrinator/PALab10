import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAdress = "127.0.0.1"; // there server's IP adress
        int PORT = 8100; // the server's port
        try{
                Socket socket = new Socket(serverAdress,PORT);
                while(true) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //Send a request to the server
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Enter command for server/client: ");
                    String request = reader.next();
                    out.println(request);
                    if(request.equals("exit"))
                        break;
                    //Wait for the response from the server
                    String response = in.readLine();
                    System.out.println(response);
                }
        } catch (UnknownHostException e){
            System.err.println("No server listening to me :( ... " + e);
        }
    }
}
