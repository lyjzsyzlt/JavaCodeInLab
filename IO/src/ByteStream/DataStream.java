package ByteStream;

import java.io.*;

public class DataStream {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("test4.txt")) {
            DataOutputStream ds=new DataOutputStream(out);
            ds.writeUTF("吕永杰");
           // ds.writeBytes("使用Byte");
            //ds.writeChars("使用char");
            ds.close();

            FileInputStream in=new FileInputStream("test4.txt");
            DataInputStream di=new DataInputStream(in);
            System.out.println(di.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
