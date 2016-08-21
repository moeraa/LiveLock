package LiveLockThread;


/**
 * Created by rxl on 16-8-9.
 */
public class LiveThreadDemo {
    public static void main(String[] args) {
        Dog wc=new Dog("旺财");
        Dog xh=new Dog("小虎");

        //旺财对面的狗是小虎
        wc.othDog=xh;
        //小虎对面的狗是旺财
        xh.othDog=wc;

        wc.bark();
//        xh.start();

        new Thread(wc).start();
        try {
             Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(xh).start();
//        wc.start();


    }

}
