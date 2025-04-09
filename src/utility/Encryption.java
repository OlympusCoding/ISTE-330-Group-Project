package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Encryption {

    public static String encrypt(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while encrypting data", e);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported encoding", e);
        }
    }
}
