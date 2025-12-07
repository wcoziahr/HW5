package coziahr;
import java.util.Objects;

/**
 * Computes mean and standard deviation using Welford's online algorithm.
 * Returns sample standard deviation (divide by n-1) when n>1.
 */
public class StatsCalculator {

    public static class StatsResult {
        public final long count;
        public final double mean;
        public final double sampleStdDev; // NaN if undefined (n < 2)

        public StatsResult(long count, double mean, double sampleStdDev) {
            this.count = count;
            this.mean = mean;
            this.sampleStdDev = sampleStdDev;
        }
    }

    /**
     * Compute statistics from our LinkedList<Double> in a single pass.
     */
    public static StatsResult computeFromLinkedList(LinkedList<Double> list) {
        Objects.requireNonNull(list, "list cannot be null");

        long n = 0;
        double mean = 0.0;
        double m2 = 0.0;

        for (Double x : list) {
            n++;
            double delta = x - mean;
            mean += delta / n;
            double delta2 = x - mean;
            m2 += delta * delta2;
        }

        double sampleStdDev = Double.NaN;
        if (n > 1) {
            sampleStdDev = Math.sqrt(m2 / (n - 1));
        }

        return new StatsResult(n, mean, sampleStdDev);
    }
}