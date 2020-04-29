import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static final int PORT = 8100;

    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            while(!serverSocket.isClosed()){
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                //nou thread pentru request-ul serverului
                new ClientThread(socket).start();
            }
        } catch( IOException e){
            System.err.println("Hopa!! " + e);
        } finally {
            serverSocket.close();
        }
    }
    public static void main(String args[]) throws  IOException {
        GameServer server = new GameServer();
    }
}
