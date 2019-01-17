public class Main {
    /**
     * Only 4 learning.
     *
     * @see java.lang.Integer#rotateLeft(int, int)
     */
    static int rotateLeft(int i, int shift) {
        return (i << shift) | (i >>> (Integer.SIZE - shift));
    }

    /**
     * Only 4 learning.
     *
     * @see java.lang.Integer#rotateRight(int, int)
     */
    static int rotateRight(int i, int shift) {
        return (i >>> shift) | (i << (Integer.SIZE - shift));
    }

    public static void main(String[] args) {
        int i = 0b10100000_11111111_11111111_10110011;
        int shift = 11;
        System.out.println(toBinaryString(i));
        System.out.println(toBinaryString(rotateLeft(i, shift)));
    }

    static String toBinaryString(int number) {
        StringBuilder sb = new StringBuilder();
        for (int j = Integer.SIZE - 1; j >= 0; --j) {
            if ((j + 1) % 8 == 0 && j != 0 && (j + 1) != Integer.SIZE) {
                sb.append('_');
            }
            sb.append((number >> j) & 0b1);
        }
        return sb.toString();
    }
}
