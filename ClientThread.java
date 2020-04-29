import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;
    public ClientThread(Socket socket) { this.socket = socket; }
    public void run() {
        try{
            while(!this.socket.isClosed()) {
                // get the request from the input stream: client --> server
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();

                //send the response to the output stream: server ---> client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String response = "Server received the request: \"" + request + "\".";

                if(response.equals("stop")){
                    out.println(response + "Server stopped by command of client");
                    out.flush();
                    try{
                        socket.close(); // or use try-with-resources
                    } catch(IOException e) { System.err.println(e); }
                    break;
                }
                out.println(response + " got executed");
                out.flush();
            }
        } catch(IOException e) {
            System.err.println("Eroare de comunicare..." + e);
        } finally {
            try{
                socket.close(); // or use try-with-resources
            } catch(IOException e) { System.err.println(e); }
        }
    }
}
