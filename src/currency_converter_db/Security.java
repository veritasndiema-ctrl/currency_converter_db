package currency_converter_db;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** Password hashing using PBKDF2 (built into the JDK - no extra libraries). */
public final class Security {

    private static final int ITERATIONS = 65536;
    private static final int KEY_BITS = 256;
    private static final String PREFIX = "pbkdf2$";

    private Security() { }

    /** Returns pbkdf2$iterations$salt$hash, safe to store in users.password_hash. */
    public static String hash(String password) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        byte[] h = derive(password.toCharArray(), salt, ITERATIONS);
        return PREFIX + ITERATIONS + "$" + Base64.getEncoder().encodeToString(salt)
                + "$" + Base64.getEncoder().encodeToString(h);
    }

    public static boolean verify(String password, String stored) {
        if (stored == null || !stored.startsWith(PREFIX)) return false;
        String[] p = stored.split("\\$");
        if (p.length != 4) return false;
        int iter = Integer.parseInt(p[1]);
        byte[] salt = Base64.getDecoder().decode(p[2]);
        byte[] expected = Base64.getDecoder().decode(p[3]);
        byte[] actual = derive(password.toCharArray(), salt, iter);
        int diff = expected.length ^ actual.length;
        for (int i = 0; i < expected.length && i < actual.length; i++) diff |= expected[i] ^ actual[i];
        return diff == 0;
    }

    public static boolean isHashed(String s) {
        return s != null && s.startsWith(PREFIX);
    }

    private static byte[] derive(char[] pw, byte[] salt, int iter) {
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return f.generateSecret(new PBEKeySpec(pw, salt, iter, KEY_BITS)).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("Password hashing unavailable", e);
        }
    }

    /** Helper used once to generate the seed hashes in schema.sql. */
    public static void main(String[] args) {
        for (String a : args) System.out.println(a + " -> " + hash(a));
    }
}
