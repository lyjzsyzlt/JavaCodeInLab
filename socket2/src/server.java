import java.awt.dnd.DropTarget;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public server() throws IOException {
        ServerSocket serverSocket=new ServerSocket(8888);
        while (true){
            Socket socket=serverSocket.accept();
            new Thread(new ServerThread(socket)).start();
        }

    }

    public static void main(String[] args) throws IOException {
        new server();
    }
}


class ServerThread implements Runnable{

    Socket socket = null;//和本线程相关的Socket
    InputStream inputStream=null;
    OutputStream outputStream=null;
    InputStreamReader inputStreamReader=null;
    BufferedReader bufferedReader=null;
    PrintWriter pw;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream=socket.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            String data=null;
            while ((data=bufferedReader.readLine())!=null){
                System.out.println("我是服务器，提交的信息为"+data);
            }
           // socket.shutdownOutput();

            outputStream=socket.getOutputStream();
            pw=new PrintWriter(outputStream);
            pw.write("服务器相应成功！");
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}