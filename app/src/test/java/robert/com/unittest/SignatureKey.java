package robert.com.unittest;

import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/**
 * Created by robert on 11/29/17.
 */
public class SignatureKey {
    @Test
    public void createSignatureKey() {
        try {
            String secret = "W572LX7LvrT79nAo6XxpCn0Q8A-mB_7kMK3Y9t1moBnqSrMkK4gvDym-bfJkiDhR";
            String message = "POST&Thu, 06 Feb 2020 06:49:52 GMT&https://vfs-api.foo-log.net/api/v1/recognize";

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            byte[] hash = (sha256_HMAC.doFinal(message.getBytes()));
            StringBuffer result = new StringBuffer();
            for (byte b : hash) {
                result.append(String.format("%02x", b)); // thanks sachins! https://gist.github.com/DucHM/43bb1d2e2826536cf9ad5d6b80d2e88a
            }
            System.out.println(result.toString()); // 93cb14fb0ee955f2c1ae63b099a15dab589823e15568a390713804bf76c61af2
        } catch (Exception e){
            System.out.println("Error");
        }
    }

}
