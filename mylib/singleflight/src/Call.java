import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Call {
    Lock callLock = new ReentrantLock();
    Condition condition = callLock.newCondition();

    Object resp;

    // 获取 call 的 resp 后，是否把 call 从它所属的 group 中移除
    boolean forgotten = true;

    // 记录有多少个重复的请求
    int dups = -1;

    int timeoutMillis = 1000; // 至多阻塞 1 s

    Call(int timeoutMillis){
        this.timeoutMillis = Math.max(this.timeoutMillis, timeoutMillis);
    }
}

