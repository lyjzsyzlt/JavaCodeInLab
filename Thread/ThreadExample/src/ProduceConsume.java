import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YongjieLv
 * @Date: 22:29 2018/4/13
 * @Description: 在一个KFC内，服务员负责生产食物，消费者负责消费食物；
                 当生产到一定数量可以休息一下，直到消费完食物，再马上生产，一直循环
 */
public class ProduceConsume {
    List<String> list;

    public ProduceConsume(){
        list=new ArrayList<>();
    }

    public void produce(){
        synchronized (this){
            while (list.size()>=5){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(list.size()==0){
                while (list.size()<5){
                    list.add(""+(list.size()));
                    System.out.println(Thread.currentThread().getName()+list);
                }
                notifyAll();
            }else {
                System.out.println("还没吃完");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void consume(){
        synchronized (this){
            while (list.size()<=0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove(list.size()-1);
            System.out.println(Thread.currentThread().getName()+"消费"+list);

                notifyAll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("释放锁");
        }
    }

    public static void main(String[] args) {
        ProduceConsume ps=new ProduceConsume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ps.produce();
            }
        },"生产者").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    ps.consume();
            }
        },"消费者").start();
    }
}
