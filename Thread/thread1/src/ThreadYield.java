public class ThreadYield {
    public static void main(String[] args) {
        Thread t1=new Thread(new ThreadYieldTest("A"));
        Thread t2=new Thread(new ThreadYieldTest("B"));
        Thread t3=new Thread(new ThreadYieldTest("C"));
        t1.setName("A");
        t2.setName("B");
        t3.setName("C");
        t1.start();
        t2.start();
        t3.start();
    }
}
