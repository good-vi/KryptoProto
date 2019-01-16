public class Main {
    public static void main(String[] args) {
        byte[] key = "passw0rd".getBytes();
        byte[] plainData = "Super secret data!".getBytes();
        System.out.println("Plain text:\n" + new String(plainData));

        ARC4 cypher = new ARC4(key);
        byte[] cipherData = cypher.encrypt(plainData);
        System.out.println("Cipher text:\n" + new String(cipherData));

        cypher.init(key); // reset
        byte[] decryptedData = cypher.decrypt(cipherData);
        System.out.println("Decrypted plain text:\n" + new String(decryptedData));
    }
}
