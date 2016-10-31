import org.junit.Assert;
import org.junit.Test;
import slidingWindowCounter.SlidingWindowCounter;

/**
 * Created by halil on 31/10/16.
 */
public class SlidingWindowTest {

    @Test
    public void expireTest() throws Exception {
        SlidingWindowCounter.getInstance().configure("localhost", 6379);
        SlidingWindowCounter.getInstance().increment("mykey", 10);

        Thread.sleep(11 * 1000);
        Assert.assertEquals(0, SlidingWindowCounter.getInstance().getCount("mykey"));
    }

    @Test
    public void incrementTest() throws Exception {
        SlidingWindowCounter.getInstance().configure("localhost", 6379);
        SlidingWindowCounter.getInstance().increment("mykey", 10);
        SlidingWindowCounter.getInstance().increment("mykey", 10);
        SlidingWindowCounter.getInstance().increment("mykey", 10);
        SlidingWindowCounter.getInstance().increment("mykey", 10);

        Assert.assertEquals(4, SlidingWindowCounter.getInstance().getCount("mykey"));
    }

}
