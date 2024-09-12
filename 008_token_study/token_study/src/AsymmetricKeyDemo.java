import java.security.KeyPairGenerator;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
public class AsymmetricKeyDemo {
    //이제 각 메서드의 인자인 키 두개가 다르다.
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        // RSA 암호화 알고리즘 사용
        Cipher cipher = Cipher.getInstance("RSA");
        // 암호화 모드로 설정 및 키 제공
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 암호화 진행하고 결과를 바이트 배열로 반환
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        // RSA 암호화 알고리즘 사용
        Cipher cipher = Cipher.getInstance("RSA");
        // 복호화 모드로 설정 및 키 제공
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 복호화 진행하고 결과를 바이트 배열로 반환
        return cipher.doFinal(data);
    }
    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048); //숫자의 크기가 2048bit(매우매우 큰 숫자를 소인수분해하는 방식. 1024도 되긴한다.숫자가 높아지면 효율이 떨어진다. 보안성은 좋아짐)
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //공개키로 암호화, 개인키로 복호화 해보기
        byte[] original = "Hello".getBytes(StandardCharsets.UTF_8);
        // 일반적인 시나리오대로,
        // 공개키로 암호화하면
        byte[] encrypted = encrypt(original, publicKey);
        // 개인키로 복호화 가능
        byte[] decrypted = decrypt(encrypted, privateKey);
        // 원본 데이터
        System.out.println("original: " + Arrays.toString(original));
        // 암호화된 데이터
        System.out.println("encrypted: " + Arrays.toString(encrypted));
        // 복호화된 데이터
        System.out.println("decrypted: " + Arrays.toString(decrypted));

        //개인키로 암호화, 공개키로 복호화
        byte[] encrypted2 = encrypt(original,privateKey);
        byte[] decrypted2 = decrypt(encrypted2,publicKey);
        System.out.println("encrypted:" + Arrays.toString(encrypted2));
        System.out.println("decrypted:" + Arrays.toString(decrypted2));

    }
}
