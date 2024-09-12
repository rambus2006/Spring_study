import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HmacJWTVerifyDemo {
    public static void main(String[] args) throws Exception {
        String secret = "your-256-bit-secret"; //절대 공유하면 안되는 키
        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c 키 토큰
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        //토큰 자르기(.기준)
        String[] content = token.split("\\.");
        //base64로 되어 있음
        // 헤더 영역
        String header_Base64URLSafe = content[0];
        // 페이로드 영역
        String payload_Base64URLSafe = content[1];
        // 디지털 서명 영역
        String signature = content[2];

        //지금 base64로 되어 있으니 디코딩 하는 과정이 필요하다.
        Base64.Decoder urlDecoder = Base64.getUrlDecoder();
        String header = new String(urlDecoder.decode(header_Base64URLSafe), StandardCharsets.UTF_8);
        String payload = new String(urlDecoder.decode(payload_Base64URLSafe), StandardCharsets.UTF_8);
        System.out.println(header);
        System.out.println(payload);

        //검증 시작
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmac.init(secretKey);

        byte[] signatureBytes = hmac.doFinal((content[0] + "." + content[1]).getBytes(StandardCharsets.UTF_8));
        //_가 있으면 url 디코딩해야 한다.
        Base64.Encoder base64urlwithoutPadding = Base64.getUrlEncoder().withoutPadding();
        String calculatedSignature = Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
        System.out.println(calculatedSignature);
        // 토큰 검증 (서로 값이 같으면 true, 아니면 false)
        System.out.println(signature.equals(calculatedSignature));
    }
}
