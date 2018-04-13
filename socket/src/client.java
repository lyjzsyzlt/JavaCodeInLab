import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
    public client(){
        try {
            Socket socket = new Socket("localhost", 8888);
            OutputStream os= socket.getOutputStream();
            PrintWriter pw=new PrintWriter(os);

            String str=null;
            Scanner in=new Scanner(System.in);
            while(true){
                str=in.next();
                if(str.equals("q"))
                    break;
                pw.write(str+"\r\n");
                pw.flush();
            }
            /*pw.write("user:lyj;password:123");
            pw.flush();*/



            socket.shutdownOutput();

            InputStream is=socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
            String data=null;
            while ((data=br.readLine())!=null){
                System.out.println("I'm server,the information client submits is"+data);
            }

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new client();
    }
}
