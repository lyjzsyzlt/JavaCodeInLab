/**
 * @Author: YongjieLv
 * @Date: 22:13 2018/4/13
 * @Description: 两个人AB通过一个账户A在柜台取钱和B在ATM机取钱
 */
public class WithDraw {

    private int Money=1000; // 银行现在总共有1000元
    private int A=100;
    private int B=200;

    public void drawMoney(int money){
        while (Money>=money){
            synchronized (this){
                if(Money>=money){
                    System.out.println(Thread.currentThread().getName()+"取出"+money+"  银行还剩"+(Money-money));
                    Money-=money;
                }
                else
                    System.out.println("银行已经没钱了");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("此时银行的钱已经不够"+Thread.currentThread().getName()+"取");

    }

    public static void main(String[] args) {
        WithDraw wd=new WithDraw();
        new Thread(new Runnable() {
            @Override
            public void run() {
                wd.drawMoney(81);
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wd.drawMoney(200);
            }
        },"B").start();
    }
}
