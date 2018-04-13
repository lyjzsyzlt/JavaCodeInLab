/**
 * @Author: YongjieLv
 * @Date: 21:54 2018/4/13
 * @Description: 三个售票窗口同时出售20张票,即总共售出20张票，不重复
 */
public class SellTickets {
    private  int count=0; // 已经卖出的票数
    private final int TicketsNum=20; // 总共20张

    public void sell(){
        while (count<20){ // 这里控制终止条件，count是三个线程的临界资源
            synchronized (this){
                if(count<TicketsNum){
                    count++;
                    System.out.println(Thread.currentThread().getName()+"号窗口售出票:"+count);
                }else{
                    System.out.println("票已经售完了");
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        SellTickets st=new SellTickets();
        new Thread(new Runnable() {
            @Override
            public void run() {
                    st.sell();
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                    st.sell();
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                    st.sell();
            }
        },"C").start();
    }

}
