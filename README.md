# Bloom Filter

Bloom Filter is a probabilistic data structure intended for solving set-membership problems in a space-efficient way (i.e. Constant space).

It consists of an array of m bits. Each time a new member is added, k of these m bits are activated using k hash functions.

Members cannot be removed once added. This is a probabilistic data structure because as the number of members increase, the probability of generating a false positive result increases.

Let n be the expected number of members to be added to the Bloom Filter.
Then, the probability of a single bit being not set by one hash function is (1 - 1 / m).
The probability of a single bit being not set by any of the n elements is (1 - 1 / m) ^ kn
Therefore, the probability of a single bit being set by any of the n elements is 1 - (1 - 1 / m) ^ kn
For a false positive result, we need a total of k bits to be set by any of the n elements.
So, probability of a false positive result is
    P = (1 - (1 - 1 / m) ^ kn) ^ k

This result is often approximated as P = (1 - e ^ (- kn / m)) ^ k using the Taylor series for e ^ (- 1 / m)

We can solve for a desired size of our bit array using fixed values of P and n:
    m = - (n ln P) / (ln 2) ^ 2

Bloom filters can never output a false negative result.

After taking the derivative w.r.t k in the equation for P and setting dP / dk = 0, we get the optimal value for k.
This value of k minimizes P for fixed values of m and n.
    k = (m / n) * ln 2

About Hash Functions:
For the best choice of Hash Functions in Bloom Filters:
    1. All Hashing functions should be fast.
    2. The outputs given by the Hashing functions should be uniformly distributed and Independant.

Usually, finding Hash functions that satisfy both of these properties is hard.

Cryptographic functions are extremely stable, but expensive to compute.
Non Cryptographic functions are less stable, but easier to compute.

# Example Implementation

Let's say we have 1000 keys and we want a false positivity rate of 10%
Then, using our formula for m, we will need a bit array of size 4800 approximately.
Using our formula for k, we will need 3 hash functions.

Let them be hash1, hash2, hash3.
I have used some standard hashing techniques for these functions.
```
algorithm hash1(key) --> int:
    output = 0x811c9dc5  // Offset basis
    for i = 0 to 4 do
        output = output ^ (key & 0xFF)
        output = output * 0x01000193  // Prime
        key = key >> 8
    return abs(output) % 4800

algorithm hash2(key) --> int:
    key = (~key) + (key << 21)
    key = key ^ (key >> 24)
    key = (key + (key << 3)) + (key << 8)
    key = key ^ (key >> 14)
    key = (key + (key << 2)) + (key << 4)
    key = key ^ (key >> 28)
    key = key + (key << 31)
    return abs(key) % 4800

algorithm hash3(key) --> int:
    key = key * 265443576
    key = key ^ (key >> 16)
    key = key * 224682251
    key = key ^ (key >> 13)
    key = key * 326648991
    key = key ^ (key >> 16)
    return abs(key) % 4800
```

# Setup

```
javac -d out *.java   
java -cp out bloom_filter.BloomFilterTest
```


