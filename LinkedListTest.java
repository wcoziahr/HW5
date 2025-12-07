package coziahr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    @Test
    public void testEmptyList() {
        LinkedList<Double> list = new LinkedList<>();
        Assertions.assertTrue(list.isEmpty());
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testAddAndGet() {
        LinkedList<Double> list = new LinkedList<>();
        list.add(1.0);
        list.add(2.5);
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1.0, list.get(0));
        Assertions.assertEquals(2.5, list.get(1));
    }

    @Test
    public void testIterationOrder() {
        LinkedList<Double> list = new LinkedList<>();
        list.add(10.0);
        list.add(20.0);
        list.add(30.0);
        double[] expected = {10.0, 20.0, 30.0};
        int i = 0;
        for (Double d : list) {
            Assertions.assertEquals(expected[i++], d);
        }
    }
}