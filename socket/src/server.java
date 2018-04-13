import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class server {
    public server(){
        try  {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket=null;

            int count=0;
            System.out.println("server is start-up,waiting clients connect");

            while (true){
                socket=serverSocket.accept();

                Thread thread=new Thread(new ServerThread(socket));
                thread.start();

                count++;
                System.out.println("the number of counts server is connected is "+count);
                InetAddress address=socket.getInetAddress();
                System.out.println("the ip of current client is "+address.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new server();
    }
}
