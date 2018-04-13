public class ThreadYieldTest implements Runnable {

    private String name;
    public ThreadYieldTest(String name){
        this.name=name;
    }


    @Override
    public void run() {
        for(int i=0;i<=50;i++){
            System.out.println(Thread.currentThread().getName()+"------"+i);
            if(i==30){
                Thread.yield();
            }
        }
    }
}
