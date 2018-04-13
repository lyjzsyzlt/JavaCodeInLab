import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerScoket {
    //服务器端
    int port= 8822;//设置的端口号
    public void UpFile(){//接受客户端上传的文件，并保存
        try {
            ServerSocket server= new ServerSocket(port);
            while(true){
                Socket socket = server.accept();
                DataInputStream is = new  DataInputStream(socket.getInputStream());
               // OutputStream os = socket.getOutputStream();
                //1、得到文件名
                String filename="F:\\DownLoad\\";
                filename += is.readUTF();
                System.out.println("新生成的文件名为:"+filename);
                FileOutputStream fos = new FileOutputStream(filename);
                byte[] b = new byte[1024];
                int length = 0;
                while((length=is.read(b))!=-1){
                    //2、把socket输入流写到文件输出流中去
                    fos.write(b, 0, length);
                }
                fos.flush();
                fos.close();
                is.close();
                socket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    public void DownFile(String filePath){
//接受客户端的下载请求，将本地文件传输给客户端
        try {
            while (true) {
                ServerSocket server= new ServerSocket(port);
                // 选择进行传输的文件
                File fi = new File(filePath);
                System.out.println("文件长度:" + (int) fi.length());
                // public Socket accept() throws
                // IOException侦听并接受到此套接字的连接。此方法在进行连接之前一直阻塞。
                Socket socket = server.accept();
                System.out.println("建立socket链接");
              /* DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
               dis.readByte();
               */
                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(socket.getOutputStream());
                //将文件名及长度传给客户端。这里要真正适用所有平台，例如中文名的处理，还需要加工，具体可以参见Think In Java 4th里有现成的代码。
                ps.writeUTF(fi.getName());
                System.out.println(fi.getName());
                ps.flush();
                int bufferSize = 8192;
                byte[] buf = new byte[bufferSize];
                while (true) {
                    int read = 0;
                    if (fis != null) {
                        read = fis.read(buf);
                    }
                    if (read == -1) {
                        break;
                    }
                    ps.write(buf, 0, read);
                }
                ps.flush();
                // 注意关闭socket链接哦，不然客户端会等待server的数据过来，
                // 直到socket超时，导致数据不完整。
                fis.close();
                socket.close();
                System.out.println("文件传输完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String arg[]) {
        //String filepath="D:\\test.txt";
        new ServerScoket().UpFile();
    }

}