package slidingWindowCounter;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * Created by halil on 31/10/16.
 */
public class SlidingWindowCounter {

    private static SlidingWindowCounter instance  = null;
    private        JedisPool            jedisPool = null;

    private SlidingWindowCounter() {
    }

    public static SlidingWindowCounter getInstance() {
        if (instance == null) {
            synchronized (SlidingWindowCounter.class) {
                if (instance == null) {
                    instance = new SlidingWindowCounter();
                }
            }
        }

        return instance;
    }

    public void configure(String host, int port) throws Exception {
        jedisPool = new JedisPool(host, port);
    }

    public void configure(JedisPool pool) throws Exception {
        jedisPool = pool;
    }

    public void increment(String key, int windowInSecond) throws Exception {

        long currentMs = System.currentTimeMillis();
        long maxScoreMs = currentMs - windowInSecond * 1000;

        Transaction redis = jedisPool.getResource().multi();
        redis.zremrangeByScore(key, 0, maxScoreMs);
        redis.zadd(key, currentMs, currentMs + "-" + Math.random()); //keep members unique
        redis.expire(key, windowInSecond);
        redis.exec();
    }

    public long getCount(String key) throws Exception {
        return jedisPool.getResource().zcard(key);
    }
}
