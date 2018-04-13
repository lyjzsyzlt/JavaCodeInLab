import java.util.concurrent.locks.Lock;

public class PrintABC {
    int a=0;
    int b=0;
    int c=1;

   public void printA(){
       synchronized (this){
           while (c==0) {
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           c=0;
           System.out.print("A");
           a=1;
           notifyAll();
       }
   }

   public void printB(){
       synchronized (this){
           while (a==0){
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           a=0;
           System.out.print("B");
           b=1;
           notifyAll();
       }
   }

   public void printC(){
       synchronized (this){
           while (b==0){
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           b=0;
           System.out.println("C");
           c=1;
           notifyAll();
       }
   }


    public static void main(String[] args) {
       PrintABC p=new PrintABC();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<15;i++){
                    p.printA();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<15;i++){
                    p.printB();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
               for(int i=0;i<15;i++){
                   p.printC();
               }
            }
        }).start();
    }


}