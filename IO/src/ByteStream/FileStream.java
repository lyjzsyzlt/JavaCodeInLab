package ByteStream;

import java.io.*;

public class FileStream {
    public static void main(String[] args) {
        File file=new File("test.txt");

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] Byte="我是吕永杰\r\nrrwe热污染翁".getBytes();
            fileOutputStream.write(Byte);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] byt=new byte[1024];
            int len=fileInputStream.read(byt);
            String str=new String(byt,0,len);
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
