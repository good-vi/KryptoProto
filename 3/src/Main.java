public class Main {
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
        System.out.println(Long.toBinaryString(number));
        System.out.println(Long.toBinaryString(swapBytes(number, 1, 3)));
    }
}
