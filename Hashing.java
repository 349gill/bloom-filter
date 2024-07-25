package bloom_filter;

public class Hashing {
    public static int hash1(int key) {
        int output = 0x811c9dc5;
        for (int i = 0; i < 4; i++) {
            output = output ^ (key & 0xFF);
            output = output * 0x01000193;
            key = key >> 8;
        }

        return Math.abs(output % 4800);
    }

    public static int hash2(int key) {
        key = (~key) + (key << 21);
        key = key ^ (key >> 24);
        key = (key + (key << 3)) + (key << 8);
        key = key ^ (key >> 14);
        key = (key + (key << 2)) + (key << 4);
        key = key ^ (key >> 28);
        key = key + (key << 31);

        return Math.abs(key) % 4800;
    }

    public static int hash3(int key) {
        key = key * 265443576;
        key = key ^ (key >> 16);
        key = key * 224682251;
        key = key ^ (key >> 13);
        key = key * 326648991;
        key = key ^ (key >> 16);

        return Math.abs(key) % 4800;
    }
}
