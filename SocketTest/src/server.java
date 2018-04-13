import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    private int port=8888;
    private ServerSocket serverSocket;

    public  server() throws IOException {
        serverSocket=new ServerSocket(8888);
    }

    // 客户端上传文件，服务器保存下来
    public void DownloadFile() throws IOException {

        while (true){
            Socket socket=serverSocket.accept();

            new Thread(new downThread(socket)).start();
        }
    }

    // 服务器上传文件供客户端下载
    public void UploadFile(String fileName) throws IOException {
        //ServerSocket serverSocket=new ServerSocket(8888);
        while (true){
            Socket socket=serverSocket.accept();
            new Thread(new upThread(socket,fileName)).start();
        }

    }


    public static void main(String []args) throws IOException {
        new server().DownloadFile();
        //new server().UploadFile("F:\\CAJViewer_7.2.113.0.exe");

        //new server().UploadFile("F:\\1.jpg");
    }
}

class downThread implements Runnable{

    Socket socket;
    public downThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            DataInputStream is=new DataInputStream(socket.getInputStream());
            String fileName="F:\\DownLoad\\"; //要保证这个文件夹存在！
            fileName+=is.readUTF();
            File file=new File(fileName);
            FileOutputStream fos=new FileOutputStream(file);

            byte[] byt=new byte[8192];
            int len=0;
            while ((len=is.read(byt))!=-1){
                fos.write(byt,0,len);
            }

            fos.flush();
            fos.close();
            is.close();
            socket.close();
            System.out.println("文件已经保存到本地！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class upThread implements Runnable{

    Socket socket;
    String fileName;

    public upThread(Socket socket,String fileName){
        this.socket=socket;
        this.fileName=fileName;
    }
    @Override
    public void run() {
        try {
            DataInputStream fis=new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            File file=new File(fileName);
            DataOutputStream ps=new DataOutputStream(socket.getOutputStream());

            ps.writeUTF(file.getName());
            ps.flush();
            byte[] byt=new byte[8192];
            while (true){
                int len=0;
                if(fis!=null){
                    len=fis.read(byt);
                }
                if(len==-1){
                    break;
                }
                ps.write(byt,0,len);
            }
            ps.flush();

            fis.close();
            ps.close();
            socket.close();
            System.out.println("文件上传到服务器成功！");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

