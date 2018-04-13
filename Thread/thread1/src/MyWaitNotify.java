public class MyWaitNotify{

    MonitorObject myMonitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWait(){
        synchronized(myMonitorObject){
            while(!wasSignalled){
                try{
                    System.out.println(Thread.currentThread().getName()+"正在等待");
                    myMonitorObject.wait();
                } catch(InterruptedException e){

                }
            }
            System.out.println(Thread.currentThread().getName()+"完成唤醒");
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (myMonitorObject) {
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }

    public static void main(String[] args) {
        MyWaitNotify mwn=new MyWaitNotify();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mwn.doWait();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                mwn.doNotify();
            }
        }).start();


    }
}

class MonitorObject {
}