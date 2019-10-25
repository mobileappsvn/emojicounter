package robert.com.unittest;

import org.junit.Test;

import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 * Created by robert on 2019-Oct-25
 */
public class ShiftJISStringUnitTest {
    @Test
    public void subString() throws Exception {

        String str = "ｼﾞﾄトナム人.ａｒｉｇａｔｏﾍﾞﾅﾑ人.ホテルメトロポリタン丸の内";
        //Convert to Fullsize - Normalize a sequence of char values
        String result = Normalizer.normalize(str, Form.NFKC);
        //SubString
        System.out.println(result.substring(0, 19));
        System.out.println(Normalizer.normalize("ＡＢＣ１２３", Form.NFKC).substring(3));

    }

}
