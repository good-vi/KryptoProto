import java.util.Random;

public class Main {
    static String shake(String s) {
        int size = s.length();
        int seed = new Random().nextInt(size);
        StringBuilder sb = new StringBuilder();

        //Caesar shift to seed
        for (int i = 0; i < size; ++i) {
            int position = (i + seed) % size;
            sb.append(s.charAt(position));
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(shake("Viktor"));
        System.out.println(shake("Viktor Kekel"));
        System.out.println(shake("Viktor Omagad"));
    }
}
