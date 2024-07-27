package bloom_filter;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter b = new BloomFilter();

        System.out.print("False Positivity before the first element: ");
        System.out.println(b.false_positivity());
        for (int i = 0; i < 4800; i++) {
            b.add((i));
        }
        System.out.print("False Positivity after the 4800th element: ");
        System.out.println(b.false_positivity());
    }
}
