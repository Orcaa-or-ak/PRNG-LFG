![image](https://github.com/user-attachments/assets/9294f0a8-2f12-47e1-8724-a830eb0c7259)# Lagged Fibonacci Generator (LFG)

This project demonstrates the implementation of a Lagged Fibonacci Generator (LFG) in Java.

## Overview

The LFG algorithm is defined by the recurrence relation:

$$
X_n = (X_{n-j} \cdot X_{n-k}) \mod m
$$

Where:
- $X$ is the sequence of pseudorandom values,
- $X_0$ is the seed or starting value,
- $X_n$ is the next number in the sequence,
- $j$ and $k$ are the lags,
- $m$ is the modulus.

## Implementation

The Java implementation of the LFG is encapsulated in a class named `LaggedFibonacciGenerator`. The BigInteger class of the java.math package is used to handle large integers, and the SecureRandom class of the java.security package is used to generate strong numbers. These two classes are suitable for cryptographic purposes.

### Class: `LaggedFibonacciGenerator`

- **Constructor**:
  ```java
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
  ```

- **Method**:
  - `public BigInteger next()`: Computes and returns the next number in the sequence using the LFG formula.

### Main Method

The `main` method demonstrates how to use the `LaggedFibonacciGenerator` class to generate and print 10 pseudorandom numbers.

```java
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
```

This will generate and print 10 pseudorandom numbers based on the LFG algorithm.
