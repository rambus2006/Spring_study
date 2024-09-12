import java.nio.charset.StandardCharsets;
import java.security.*;

public class DigitalSignatureDemo {
    public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
        /*
        "SHA256withRSA"는 디지털 서명 알고리즘의 조합으로 다음 두 가지 요소를 포함
        1) 해시 알고리즘은 SHA-256을 사용
        2) 비대칭키 암호화 알고리즘은 RSA를 사용

        해시, 비대칭키 암호화 알고리즘 조합의 작동 방식
        1) 서명 과정
            a. 먼저 원본 데이터를 SHA-256 알고리즘으로 해시
            b. 그 다음 해시값을 RSA 개인키로 암호화하여 서명을 생성
        2) 검증 과정:
            a. 원본 데이터를 SHA-256으로 해시
            b. 서명을 RSA 공개키로 검증하여 원본 해시값을 얻음
            c. 두 해시값을 비교하여 일치 여부를 확인
        */
        Signature signature = Signature.getInstance("SHA256withRSA"); //해시함수 알고리즘은 SHA256,개인키 알고리즘은 RSA
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    //검증
    public static boolean verify(byte[] data, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        //내부적으로 true, false로 비교해서 리턴해준다.
        return signature.verify(signatureBytes); //true 이면 데이터가 조작되지도 않았고, 개인키로 서명된 값이다.
    }
    public static void main(String[] args)throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        byte[] data = "Hello".getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = sign(data,keyPair.getPrivate()); //리턴하는 내용
        boolean isVaid = verify(data,signatureBytes,keyPair.getPublic());
    }

}
