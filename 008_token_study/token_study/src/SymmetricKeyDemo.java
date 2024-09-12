import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class SymmetricKeyDemo  {
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    //인풋 값, 암호화키
    public static byte[] encrypt(byte[] data, SecretKey key) throws Exception {
        // AES 암호화 알고리즘 사용
        Cipher cipher = Cipher.getInstance("AES");
        // 암호화 모드로 설정 및 키 제공
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 암호화 진행하고 결과를 바이트 배열로 반환
        return cipher.doFinal(data);
    }
    //암호화된 값, 복호화키(=암호화키)
    public static byte[] decrypt(byte[] data, SecretKey key) throws Exception {
        // AES 암호화 알고리즘 사용
        Cipher cipher = Cipher.getInstance("AES");
        // 복호화 모드로 설정 및 키 제공
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 복호화 진행하고 결과를 바이트 배열로 반환
        return cipher.doFinal(data);
    }
    public static void main(String[] args) throws Exception{
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(256);
//        SecretKey secretKey = keyGen.generateKey();
        SecureRandom secureRandom = new SecureRandom();
        // AES-128, AES-192, AES-256가 지원되지만, 여기서는 32바이트 키 사용
        int keySize = 32;
        byte[] randomBytes = new byte[keySize];
        secureRandom.nextBytes(randomBytes); //secureRandom : 메서드,운영체제의 도움을 받아 난수를 만든다.
        SecretKeySpec secretKey = new SecretKeySpec(randomBytes, "AES");

        byte[] original = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[]encrypted = encrypt(original,secretKey);
        byte[] decrypted = decrypt(encrypted,secretKey);

        System.out.println("original: " + Arrays.toString(original));
        // 암호화된 데이터
        System.out.println("encrypted: " + Arrays.toString(encrypted));
        // 복호화된 데이터
        System.out.println("decrypted: " + Arrays.toString(decrypted));


    }

}
