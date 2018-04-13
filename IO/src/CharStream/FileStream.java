package CharStream;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStream {
    public static void main(String[] args) throws IOException {
        File file=new File("test2.txt");
        FileWriter out=new FileWriter(file);
        String str="jfeiowfj\r\n吕永杰";
        out.write(str);
        out.close();

        FileReader in=new FileReader(file);
        char[] byt=new char[1024];
        int len=in.read(byt);
        String str2=new String(byt,0,len);
        System.out.println(str2);
        in.close();
    }
}
