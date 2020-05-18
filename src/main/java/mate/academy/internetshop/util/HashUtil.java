package mate.academy.internetshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;

public class HashUtil {
    public static Logger logger = Logger.getLogger(HashUtil.class);

    public static byte[] getSalt() {
        var random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        var hashedPassword = new StringBuilder();
        try {
            var messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return hashedPassword.toString();
    }
}
