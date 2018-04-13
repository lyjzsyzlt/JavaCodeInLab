/**
 * 龟兔赛跑总共100米，兔子每秒5米，乌龟每秒1米，兔子每跑20米休息10秒
 * 其中一个跑到终点后另一个则停止
 */
public class RabbitTurtle {
    private int All=100;
    private int v1=5;
    private int v2=1;
    volatile boolean flag=false;
    private int s1=0;
    private int s2=0;

    private int who=0;

    public void rabbit(){
        synchronized (this) {

            while (who==1){// 不是兔子走
                if(s1==All){
                    flag=true;
                    return;
                }
                if(s1%20==0&&s1!=0){
                    who=1;
                }

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            s1+=v1;
            System.out.println(Thread.currentThread().getName()+"跑了"+s1);
            notifyAll();

        }
    }

    public void turtle(){
        synchronized (this){
            while (who==0){//不是乌龟走
                if(s2==All){
                    flag=true;
                    return;
                }

                try {
                    wait();
                    who=1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(s1%20==0&&s1!=0){
                for(int i=0;i<10;i++){
                    s2+=v2;
                    System.out.println(Thread.currentThread().getName()+"跑了"+s2);
                }
            }
            s2+=v2;
            System.out.println(Thread.currentThread().getName()+"-跑了"+s2);
            who=0;
            notifyAll();

        }
    }
}

class Test{
    public static void main(String[] args) {
        RabbitTurtle rt=new RabbitTurtle();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!rt.flag){
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    rt.rabbit();
                }
                System.out.println("tuzi");
            }
        },"兔子").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!rt.flag){
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    rt.turtle();
                }
                System.out.println("wugui");
            }
        },"乌龟").start();


    }
}

