package bloom_filter;

import java.util.*;

public class BloomFilter {
    ArrayList<Integer> hash;
    static int size = 4800; // Size of Bloom Filter
    int members = 0;

    public BloomFilter() {
        this.hash = new ArrayList<Integer>(Collections.nCopies(size, 0));
    }

    public void add(int n) {
        hash.set(Hashing.hash1(n), 1);
        hash.set(Hashing.hash2(n), 1);
        hash.set(Hashing.hash3(n), 1);
        members++;
    }

    public double false_positivity() {
        double k = 3;
        double m = size;
        double n = members;
        double exponent = Math.exp(-k * n / m);
        return Math.pow(1 - exponent, k);
    }

    public boolean lookup(int key) {
        return hash.get(Hashing.hash1(key)) == 1 
            && hash.get(Hashing.hash2(key)) == 1 
            && hash.get(Hashing.hash3(key)) == 1;
    }

}
