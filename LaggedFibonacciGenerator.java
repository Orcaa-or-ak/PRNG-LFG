import java.math.BigInteger;
import java.security.SecureRandom;

// Produces a sequence of numbers using the Lagged Fibonacci Generator
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
        int bitLength = 512;

        // Lags for the generator
        int j = 5;
        int k = 17;

        // Seed value
        BigInteger seed = new BigInteger("3141592654");

        LaggedFibonacciGenerator lfg = new LaggedFibonacciGenerator(bitLength, j, k, seed);

        // Generate and print 10 random numbers
        for (int i = 0; i < 10; i++) {
            System.out.println(lfg.next());
            System.out.println();
        }
    }
}
