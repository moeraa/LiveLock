package LiveLockThread;

/**
 * Created by rxl on 16-8-9.
 */
public class Dog implements Runnable{
    public Dog othDog;//另一个狗
    private String name;
    private boolean bark;//狗叫

    public Dog(String name) {
        this.name=name;
    }

    /**
     * 狗叫了
     */
    public void bark(){
        this.bark=true;
    }

    /**
     * 狗没叫
     */
    public void unbark(){
        this.bark=false;
    }

    @Override
    public void run() {
        while (true){
            if (othDog.bark){//如果另一个狗叫了
                // 你也叫
                this.bark();
                try {
                    System.out.println(this.name+"-->旺财正在叫!");
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.unbark();//500毫米后不叫了

            }else {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (othDog.bark){//如果另一个狗叫了
                // 你也叫
                this.bark();
                try {
                    System.out.println(this.name+"-->小虎正在叫!");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.unbark();//500毫米后不叫了

            }else {
                break;
            }
        }

    }

//    public void start() {
//    }
}
