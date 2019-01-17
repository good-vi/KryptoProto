public class Main {
    /**
     * Swap bytes in <code>number</code>. Bytes are numerated from 0:
     * Long: [7][6][5][4][3][2][1][0]
     * @param number input number
     * @param i first byte position
     * @param j second byte position
     * @return
     */
    static long swapBytes(long number, long i, long j) {
        // j gotta be hight bit: long{[][][j][][][i][][]}
        if (j < i) {
            long tmp = j;
            j = i;
            i = tmp;
        }

        // Swap bytes
        long jByte = (number >> (j * 8)) & 0xFF;
        jByte = jByte << (i * 8);

        long iByte = (number >> (i * 8)) & 0xFF;
        iByte = iByte << (j * 8);

        long swapedBytesMask = iByte | jByte;
        
        // Prepare number for adding swapedBytesMask
        long cleanerMask = ~0L; // == 0b111..111
        long leftPartOfCleanerMask = ((cleanerMask >> (j * 8)) & 0xFF) << (j * 8);
        long rightPartOfCleanerMask = ((cleanerMask >> (i * 8)) & 0xFF) << (i * 8);
        cleanerMask = leftPartOfCleanerMask | rightPartOfCleanerMask;
        number = number & (~cleanerMask);

        return number | swapedBytesMask;
    }

    public static void main(String[] args) {
        long number = 0b10110111_00110011_01010101_10010100L;
        System.out.println(toBinaryString(number));
        System.out.println(toBinaryString(swapBytes(number, 1, 3)));
    }

    static String toBinaryString(long number) {
        StringBuilder sb = new StringBuilder();
        for (int j = Long.SIZE - 1; j >= 0; --j) {
            if ((j + 1) % 8 == 0 && j != 0 && (j + 1) != Long.SIZE) {
                sb.append('_');
            }
            sb.append((number >> j) & 0b1);
        }
        return sb.toString();
    }
}