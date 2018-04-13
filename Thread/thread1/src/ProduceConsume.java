import java.util.Vector;

/*
生产者消费者
 */
public class ProduceConsume {
    private Vector<String> goods; // 临界资源
    private final int SIZE=5;

    public ProduceConsume(){
        goods=new Vector<String>();
    }

    // 生产者
    public synchronized void production(){
        if(goods.size()<SIZE){
            goods.add("p"+(goods.size()+1));
            System.out.println(Thread.currentThread().getName()+"-货物："+goods);
            if(goods.size()==SIZE)
                System.out.println("仓库已满");
            notifyAll();
        }else{
            try {

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void Consume(){
        if(goods.size()>=1){
            goods.remove(goods.size()-1);
            System.out.println(Thread.currentThread().getName()+"-货物"+goods);
            if(goods.size()==0)
                System.out.println("货物已经取完");
            notifyAll();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsume ps=new ProduceConsume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    ps.production();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"生产者1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    ps.Consume();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"消费者1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    ps.production();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"生产者2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    ps.Consume();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"消费者2").start();

    }
}
