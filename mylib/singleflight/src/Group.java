import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考 golang singleflight 源码实现
 * 缺点：某个 map 元素入队出队的时候要锁住整个 map。这是为了保证同一时刻对同一个 key，map 中只能有一个元素
 */
public class Group {
    Lock groupLock = new ReentrantLock();
    Map<String, Call> map = null;

    public Call doGroup(String key, Function fn, int timeoutMillis) throws InterruptedException {
        groupLock.lock();

        if (map == null){
            map = new HashMap<>();
        }

        if (map.containsKey(key)){
            Call c = map.get(key);

            c.dups++;
            groupLock.unlock();

            try {
                c.callLock.lock();
                c.condition.await(c.timeoutMillis, TimeUnit.MILLISECONDS); // 有可能 c 已经 notify，还没从 map 中移除的时候，这个线程又等待了，它无法被正常唤醒。
            } finally {
                c.callLock.unlock();
            }
            return c; // 假如执行到这里的时候，c已经被从map中移除，仍能正常return，因为这里并不是 return map.get(key)
        }

        Call c = new Call(timeoutMillis);
        map.put(key, c);
        groupLock.unlock();
        doCall(key, c, fn);

        return c;
    }

    private void doCall(String key, Call c, Function fn) {
        c.resp = fn.execute(key);

        try {
            c.callLock.lock();
            c.condition.signalAll();
        } finally {
            c.callLock.unlock();
        }

        groupLock.lock();
        if (c.forgotten){
            map.remove(key); // map不再引用c，c如果还有别的地方在使用的话不会被gc，否则可能很快被gc
        }
        groupLock.unlock();
    }
}




