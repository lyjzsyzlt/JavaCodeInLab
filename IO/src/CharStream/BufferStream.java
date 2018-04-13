package CharStream;

import java.io.*;

public class BufferStream {
    public static void main(String[] args) throws IOException {
        File file=new File("test3.txt");
        FileWriter fw=new FileWriter(file);
        BufferedWriter bufw=new BufferedWriter(fw);
        String []str={"lyj","吕永杰","@#$*&_"};
        for(int i=0;i<str.length;i++){
            bufw.write(str[i]);
            bufw.write("\r\n");
        }
        bufw.close();

        FileReader fr=new FileReader(file);
        BufferedReader bufr=new BufferedReader(fr);
        String str1=null;
        while ((str1=bufr.readLine())!=null){
            System.out.println(str1);
        }

    }
}
