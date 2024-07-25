package bloom_filter;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter b = new BloomFilter();

        for (int i = 0; i < 4800; i++) {
            b.add((i));
            System.out.print("False Positivity: ");
            System.out.println(b.false_positivity());
        }
    }
}
