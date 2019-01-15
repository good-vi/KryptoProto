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
        System.out.println(rotateLeft(i,11));
    }


}
