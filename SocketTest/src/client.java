

import java.io.*;
import java.net.Socket;

public class client {
    private int port;
    private String IP;
    private Socket socket=null;

    public client(String IP,int port){
        this.IP=IP;
        this.port=port;

    }

    // 上传文件到服务器
    public void UploadFile(String fileName){
        File file=new File(fileName);
        System.out.println("文件目录："+fileName+'\t'+"文件大小："+file.length());

        try {
            socket=new Socket(IP,port);
            DataInputStream fis=new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));// 本地文件的一个Data流
            OutputStream os=socket.getOutputStream();
            DataOutputStream ps=new DataOutputStream(os);// 将客户端的输出流包装成Data流
            ps.writeUTF(file.getName());// 供服务器读取这个文件的名称，然后为其建立本地路径
            ps.flush();

            // 将本地文件内容传送到客户端的输出流
            byte[] byt=new byte[8192];
            int process=0;
            long fileLen=file.length();
            while (true){
                int len=0;
                if(fis!=null){
                    len=fis.read(byt);
                    process+=len;
                }
                if(len==-1){
                    break;
                }
                //System.out.println("传输中："+(float)process/fileLen*100+"%");
                ps.write(byt,0,len);
            }
            ps.flush();

            fis.close();
            socket.close(); //记得关闭socket
            System.out.println("文件上传完成");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从服务器上下载文件到本地
    public void DownloadFile(){
        try {
            Socket socket=new Socket(IP,port);
            DataInputStream is=new DataInputStream(socket.getInputStream());

            // 为将要下载的文件建立本地路径
            String fileName="F:\\client\\"; //要保证这个文件夹存在！
            fileName+=is.readUTF();
            FileOutputStream fos=new FileOutputStream(fileName);

            // 将客户端的输入流内容传送到本地的文件输出流中，写入磁盘
            int len=0;
            byte[] byt=new byte[8196];
            while ((len=is.read(byt))!=-1){
                fos.write(byt,0,len);
            }
            fos.flush();
            fos.close();
            is.close();
            socket.close();
            System.out.println("文件下载成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 要保证服务器和客户端一个上传一个下载。。可以开多个客户端上传，服务器只下载，或者开多个客户端下载，服务器只上传
    public static void main(String[] args) {
        String fileName="F:\\ideaIC-2017.3.4.exe";
        new client("172.23.1.24",8888).UploadFile(fileName);
        //new client("172.23.1.24",8888).DownloadFile();
    }
}
