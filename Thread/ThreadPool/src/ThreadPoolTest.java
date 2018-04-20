import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("use thread pool to execute Runnable tasks:");
        ExecutorService threadPool=Executors.newFixedThreadPool(5);
        List<AccumRunnable> tasks=new ArrayList<>(10);
        for(int i=0;i<10;i++){
            AccumRunnable task=new AccumRunnable(i,i*10+1,(i+1)*10);
            tasks.add(task);
            threadPool.execute(task);
        }
        threadPool.shutdown();

        threadPool.awaitTermination(1,TimeUnit.HOURS);
        for(AccumRunnable task:tasks){
            System.out.println(task.getResult());
        }
    }


    static final class AccumRunnable implements Runnable{
        private final int begin;
        private final int end;
        private final int i;
        private int result;
        public AccumRunnable(int i,int begin,int end) {
            this.begin=begin;
            this.end=end;
            this.i=i;
        }

        @Override
        public void run() {
            result=0;
            try {
                for (int i=begin;i<=end;i++){
                    result+=i;
                    Thread.sleep(100);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.printf("(%s)-run finished,%d-result is %d\n",Thread.currentThread().getName(),i,result);
        }

        public int getResult(){
            return result;
        }
    }
}

