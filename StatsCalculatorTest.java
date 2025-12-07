package coziahr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatsCalculatorTest {

    @Test
    public void testWelfordKnownData() {
        LinkedList<Double> list = new LinkedList<>();
        // Data: 2, 4, 4, 4, 5, 5, 7, 9 (classic example)
        double[] data = {2,4,4,4,5,5,7,9};
        for (double d : data) list.add(d);
        StatsCalculator.StatsResult r = StatsCalculator.computeFromLinkedList(list);
        Assertions.assertEquals(8, r.count);
        Assertions.assertEquals(5.0, r.mean, 1e-9);
        // sample standard deviation known: sqrt(32/7) ≈ 2.138089935
        Assertions.assertEquals(Math.sqrt(32.0/7.0), r.sampleStdDev, 1e-9);
    }

    @Test
    public void testSingleElement() {
        LinkedList<Double> list = new LinkedList<>();
        list.add(42.0);
        StatsCalculator.StatsResult r = StatsCalculator.computeFromLinkedList(list);
        Assertions.assertEquals(1, r.count);
        Assertions.assertEquals(42.0, r.mean, 1e-9);
        Assertions.assertTrue(Double.isNaN(r.sampleStdDev));
    }

    @Test
    public void testEmpty() {
        LinkedList<Double> list = new LinkedList<>();
        StatsCalculator.StatsResult r = StatsCalculator.computeFromLinkedList(list);
        Assertions.assertEquals(0, r.count);
        Assertions.assertEquals(0.0, r.mean, 1e-9); // mean remains 0.0 by algorithm
        Assertions.assertTrue(Double.isNaN(r.sampleStdDev));
    }
}