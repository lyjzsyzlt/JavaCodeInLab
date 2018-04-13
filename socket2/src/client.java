import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class client{
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",8888);
        OutputStream out=socket.getOutputStream();
        PrintWriter pw=new PrintWriter(out);
        Scanner scanner=new Scanner(System.in);
        while (true){
            String str=scanner.next();
            if(str.equals("q"))
                break;
           pw.write(str);
           pw.write("\r\n");
           pw.flush();
        }

        socket.shutdownOutput();

        InputStream in=socket.getInputStream();
        InputStreamReader is=new InputStreamReader(in,"utf-8");
        BufferedReader br=new BufferedReader(is);
        String data=null;
        while ((data=br.readLine())!=null){
            System.out.println(data);
        }
    }
}