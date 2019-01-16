/**
 * @see <a href="https://www.intuit.ru/studies/courses/28/28/lecture/20424?page=2">Simple XOR hash</a>
 */
class SimpleXorHash {
    private byte[] input;
    private int iterations;
    private long block = 0L;
    private final int blockSize = Long.BYTES;

    SimpleXorHash(int iterations) {
        this.iterations = iterations;
    }

    SimpleXorHash(byte[] input, int iterations) {
        this.input = input;
        this.iterations = iterations;
    }

    long digest() {
        int numberOfBlocksInInput = input.length / blockSize;
        // add block if data structure have alignment
        numberOfBlocksInInput += (input.length % blockSize > 0) ? 1 : 0;

        for (int j = 0; j <= numberOfBlocksInInput; ++j) {
            // collect blocks from 8 bytes
            long nextBlock = 0L;
            int startPosition = j * Byte.SIZE;
            for (int k = 0; ((startPosition + k) < input.length) && k < blockSize; ++k) {
                nextBlock = (nextBlock << Byte.SIZE);
                nextBlock = nextBlock | input[startPosition + k];
            }
            // do iterations
            for (int i = 0; i < iterations; ++i) {
                step(nextBlock);
            }
        }
        return block;
    }

    void reset() {
        block = 0L;
    }

    private void step(long nextBlock) {
        block = Long.rotateLeft(block, 1);
        block = block ^ nextBlock;
    }

    public SimpleXorHash setInput(byte[] input) {
        this.input = input;
        return this;
    }

    public int getIterations() {
        return iterations;
    }

    public SimpleXorHash setIterations(int iterations) {
        this.iterations = iterations;
        return this;
    }
}

public class Main {
    public static void main(String[] args) {
        String str = "Viktor Kovalev";
        String str1 = "Viktor Kovalea";
        SimpleXorHash hash = new SimpleXorHash(16);
        hash.setInput(str.getBytes());

        System.out.println("Digest 4 input:[" + str + "] is: " + hash.digest() + " with iterations: " + hash.getIterations());
        hash.reset();
        hash.setIterations(32);
        System.out.println("Digest 4 input:[" + str + "] is: " + hash.digest() + " with iterations: " + hash.getIterations());
        hash.reset();

        hash.setIterations(16).setInput(str1.getBytes());
        System.out.println("Digest 4 input:[" + str1 + "] is: " + hash.digest() + " with iterations: " + hash.getIterations());

        hash.setIterations(32);
        System.out.println("Digest 4 input:[" + str1 + "] is: " + hash.digest() + " with iterations: " + hash.getIterations());
    }
}
