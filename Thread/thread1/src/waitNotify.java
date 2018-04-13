public class waitNotify {
    private boolean isAWaiting;
    private boolean isBWaiting;
    private boolean isCWaiting;

    public waitNotify(){
        isAWaiting=true;
        isBWaiting=true;
        isCWaiting=true;
    }

    private byte[] bytes=new byte[0];

    public void startA(){
        synchronized (bytes){
            System.out.println("线程A正在等待...");
            try {
                while (true) {
                    if (!isAWaiting)
                        break;
                    bytes.wait();
                }
            }catch(InterruptedException e) {
                e.printStackTrace();
                }
                System.out.println("线程A结束");
            wakenB();
            }
    }

    public void startB(){
        synchronized (bytes){
            System.out.println("线程B正在等待...");
            try{
                while (true){
                    if(!isBWaiting)
                        break;
                    bytes.wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程B结束");
            wakenC();
        }
    }

    public void startC(){
        synchronized (bytes){
            System.out.println("线程C正在等待...");
            try{
                while (true){
                    if(!isCWaiting)
                        break;
                    bytes.wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程C结束");
        }
    }


    public void startWaken(){
        synchronized (bytes){
            System.out.println("唤醒开始..");
            wakenA();
        }
    }

    public void wakenA(){
        isAWaiting=false;
        bytes.notify();
    }

    public void wakenB(){
        isBWaiting=false;
        bytes.notify();
    }
    public void wakenC(){
        isCWaiting=false;
        bytes.notify();
    }


    public static void main(String[] args) {
        waitNotify w=new waitNotify();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                w.startA();
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                w.startB();
            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                w.startC();
            }
        });

        Thread t4=new Thread(new Runnable() {
            @Override
            public void run() {
                w.startWaken();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}






