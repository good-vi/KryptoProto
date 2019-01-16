public class ARC4 {
    private final byte[] S = new byte[256];

    public ARC4(final byte[] Key) throws IllegalArgumentException {
        init(Key);
    }

    /**
     * Init/reset init state (S matrix).
     *
     * @param Key array of bytes, containing new key
     */
    public void init(final byte[] Key) {
        if (Key.length < 1 || Key.length > 256) {
            throw new IllegalArgumentException("Wrong key size (1<=length<=256)");
        } else {
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) (i & 0xFF);
            }
            int j = 0;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + Key[i % Key.length]) & 0xFF;
                S[i] ^= S[j];
                S[j] ^= S[i];
                S[i] ^= S[j];
            }
        }
    }

    public byte[] encrypt(final byte[] plaintext) {
        final byte[] ciphertext = new byte[plaintext.length];
        int i = 0, j = 0, K, t;
        byte[] _S = S;
        for (int counter = 0; counter < plaintext.length; counter++) {
            i = ((i + 1) % 256) & 0xFF;
            j = ((j + _S[i]) % 256) & 0xFF;
            _S[i] ^= _S[j];
            _S[j] ^= _S[i];
            _S[i] ^= _S[j];
            t = ((_S[i] + _S[j]) % 256) & 0xFF;
            K = _S[t];
            ciphertext[counter] = (byte) ((plaintext[counter] ^ K) & 0xFF);
        }
        return ciphertext;
    }

    public byte[] decrypt(final byte[] ciphertext) {
        return encrypt(ciphertext);
    }

}