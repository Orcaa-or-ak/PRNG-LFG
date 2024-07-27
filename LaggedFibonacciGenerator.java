import java.math.BigInteger;
import java.security.SecureRandom;

public class LaggedFibonacciGenerator {
    private BigInteger[] state;
    private int j;
    private int k;
    private int index;
    private BigInteger modulus;

    public LaggedFibonacciGenerator(int bitLength, int j, int k, BigInteger seed) {
        this.j = j;
        this.k = k;
        this.index = 0;
        this.modulus = BigInteger.TWO.pow(bitLength);

        // Initialize the state array with the seed
        state = new BigInteger[k];
        SecureRandom random = new SecureRandom(seed.toByteArray());

        for (int i = 0; i < k; i++) {
            state[i] = new BigInteger(bitLength, random).mod(modulus);
        }
    }

    public BigInteger next() {
        int i1 = (index - j + state.length) % state.length;
        int i2 = (index - k + state.length) % state.length;

        state[index] = state[i1].add(state[i2]).mod(modulus);
        BigInteger result = state[index];

        index = (index + 1) % state.length;

        return result;
    }

    public static void main(String[] args) {
        int bitLength = 2048;

        // Lags for the generator
        int j = 5;
        int k = 17;

        // Seed value
        BigInteger seed = new BigInteger("3141592654");

        LaggedFibonacciGenerator lfg = new LaggedFibonacciGenerator(bitLength, j, k, seed);

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();

        // Generate and print 10 random numbers along with CPU, RAM, and time cost
        for (int i = 0; i < 10; i++) {
            // Measure start time and memory usage
            long startTime = System.nanoTime();
            long startMemory = runtime.totalMemory() - runtime.freeMemory();

            // Generate 1000 random numbers
            for (int z = 0; z < 1000; z++) {
                lfg.next();
            }
            
            // Measure end time and memory usage
            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();
            
            // Calculate total time taken and memory used
            double totalTimeTakenMs = (endTime - startTime) / 1_000_000.0;
            long totalUsedMemoryBytes = endMemory - startMemory;

            // Calculate average time and memory used per number
            double averageTimeTakenMs = totalTimeTakenMs / 1000;
            double averageUsedMemoryBytes = totalUsedMemoryBytes / 1000.0;

            // Generate one final random number to print
            BigInteger randomNumber = lfg.next();

            System.out.println("Random Number: " + randomNumber);
            System.out.println("Bit Length: " + randomNumber.bitLength());
            System.out.println("Average Time taken: " + averageTimeTakenMs + " ms");
            System.out.println("Average Used Memory: " + averageUsedMemoryBytes + " Bytes");
            System.out.println();
        }
    }
}
