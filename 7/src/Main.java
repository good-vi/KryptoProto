import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static final int MASK32 = 0xffffffff;

    public static void main(String[] args) {
        String inputString = "Input string";
        Random generator1 = new Random();
        Random generator2 = new Random();

        Path path = Paths.get("out.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            inputString.chars().map(ch -> {
                int tmp = ch ^ getRandomByte(generator1);
                tmp = (tmp + getRandomByte(generator2)) & MASK32;
                return tmp;
            }).forEach(out -> {
                try {
                    writer.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static int getRandomByte(Random generator) {
        int min = Byte.MIN_VALUE;
        int max = Byte.MAX_VALUE;
        int randomNum = generator.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
