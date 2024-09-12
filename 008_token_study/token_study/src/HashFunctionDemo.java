import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

//해시함수의 뜻과 특징만 기억해두자.
public class HashFunctionDemo {
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public static void main(String[] args)throws Exception{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[]bytes = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[]hash = digest.digest(bytes);
        System.out.println(hash.length); //32byte

        String hexString = bytesToHex(hash);
        System.out.println(hexString);


    }
}
