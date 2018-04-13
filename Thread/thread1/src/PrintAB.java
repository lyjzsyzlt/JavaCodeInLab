public class PrintAB {
    boolean flag=true;

    public synchronized void printA(){
        while (!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        flag=false;
        notifyAll();
    }

    public synchronized void printB(){
        while (flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        flag=true;
        notifyAll();
    }
}
class Test1{
    public static void main(String[] args) {
        PrintAB p=new PrintAB();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++)
                    p.printB();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++)
                    p.printA();
            }
        }).start();


    }
}