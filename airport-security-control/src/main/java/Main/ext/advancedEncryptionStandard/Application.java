package Main.ext.aes;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Arrays;

public class Application
{
    private SecretKeySpec secretKey;

    public static void main(String... args) throws Exception {
        Application application = new Application();

        final String secretKey = "dhbw$20^20_";

        String plainMessage = "***I***0614***";
        String encryptedString = application.encrypt(plainMessage, secretKey);
        application.decrypt(encryptedString, secretKey);

    }

    public String decrypt(String encryptedMessage, String key) throws Exception {
        setKey(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
    }

    public void setKey(String inputKey) throws Exception {
        MessageDigest sha;

        byte[] key = inputKey.getBytes(StandardCharsets.UTF_8);
        sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        secretKey = new SecretKeySpec(key, "AES");
    }

    public String encrypt(String plainMessage, String key) throws Exception {
        setKey(key);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainMessage.getBytes(StandardCharsets.UTF_8)));
    }
}
