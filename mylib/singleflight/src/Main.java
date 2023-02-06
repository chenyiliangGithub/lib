import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger count = new AtomicInteger();
        Group group = new Group();

        for (int i=0;i<1000;++i){
            executorService.submit(() -> { // 任务放到线程池中，执行时间不确定
                return group.doGroup("key", e->{
                    System.out.println(count.getAndIncrement()+e);
                    try {
                        Thread.sleep(100); // 执行久一点时间，测试防穿透效果
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    return e;
                    }, 200);
            });

        }

    }
}
