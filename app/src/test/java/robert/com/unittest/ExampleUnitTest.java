package robert.com.unittest;

import com.mobileappsvn.emojicounter.DateTimeUtils;

import org.junit.Test;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by robert on 11/29/17.
 */
public class ExampleUnitTest {
    @Test
    public void findRegex() throws Exception {
        //String mRegex = "(abckmlxyz|abc4563232|abc65653232)";//true
        String mRegex = "^(abc)(kmlxyz|4563232|65653232)";

        String str = "abckmlxyz";

        Pattern pattern = Pattern.compile(mRegex);

        Matcher matcher = pattern.matcher(str);

        boolean match = matcher.matches();
        System.out.println("Match = " + match);

        long loginTime = DateTimeUtils.getLongTime("20190228044336", "yyyyMMddHHmmss", TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
        System.out.println(loginTime);
        System.out.println(DateTimeUtils.convertUnixMillisecondsToString(loginTime,"yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone("GMT"), Locale.ENGLISH));

        String usalbeVersionElement = ".11";
        String applicationVersionElement = ".9";

        double usableVersionE = Double.parseDouble(usalbeVersionElement);
        double applicationVersionE = Double.parseDouble(applicationVersionElement);

        if (applicationVersionE < usableVersionE) {
            System.out.println("UNUSABLE_VERSION_APPLICATION");
        } else if (applicationVersionE > usableVersionE) {
            System.out.println("SUCCESS-applicationVersionE=" + applicationVersionE);
        }

        System.out.println("===============================");

        String usableVersionString = "1.11";
        String applicationVersionString = "1.9";

        double usableVersion = Double.parseDouble(usableVersionString);//1.11;//
        double applicationVersion = Double.parseDouble(applicationVersionString);//1.9;//

        if (applicationVersion < usableVersion) {
            System.out.println("=>UNUSABLE_VERSION_APPLICATION");
        } else if (applicationVersion > usableVersion) {
            System.out.println("=>SUCCESS - applicationVersion=" + applicationVersion);
        }
        System.out.println("===============================");
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (double i = 1.0; i <= 5.5; i=i+0.1) {
            System.out.format("%.2f; ", i);
            //System.out.printf("%.2f; ", i);
            //System.out.print(formatter.format(i) + "; ");
        }

        System.out.println("\n===============================");
        double d = 123456.7890;
        DecimalFormat df = new DecimalFormat("#####0.00");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();

        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        System.out.println(df.format(d));

        System.out.println("\n===============================");

        String stringWithUnicode = "\\uFF7C\\uFF9E\\uFF84\\u30C8\\u30CA\\u30E0\\u4EBA\\uFF12\\u6708\\u2139\\uFE0F\\uD83C\\uDFF4\\u2049\\uFE0F\\uD83D\\uDC68\\u200D\\u2764\\uFE0F\\u200D\\uD83D\\uDC8B\\u200D\\uD83D\\uDC68\\uD83D\\uDC68\\u200D\\uD83D\\uDC68\\u200D\\uD83D\\uDC66\\u200D\\uD83D\\uDC66\\uD83D\\uDC69\\u200D\\uD83D\\uDC69\\u200D\\uD83D\\uDC66\\u200D\\uD83D\\uDC66\\uD83C\\uDF0D\\uD83D\\uDDFE\\u00AE\\uFE0F\\u2139\\uFE0F\\uD83C\\uDE35\\uD83C\\uDFF4\\u0031\\u0040\\uFF41\\uFF52\\uFF49\\uFF47\\uFF41\\uFF54\\uFF4F\\uFF8D\\uFF9E\\uFF85\\uFF91\\u4EBA\\u0061\\u0072\\u0069\\u0067\\u0061\\u0074\\u006F\\u30AE\\u30E3\\u7537\\u300D\\u3067\\u3057\\u305F\\u3002";

        Map<String, WordCounter.WordCount> numberWords = WordCounter.countWords(Normalizer.normalize(stringWithUnicode, java.text.Normalizer.Form.NFKC), Locale.JAPANESE);
        System.out.println("\n===>numberWords=" + numberWords.size());

        for (Map.Entry<String, WordCounter.WordCount> word: numberWords.entrySet()) {
            System.out.println("------>word=" + word.getKey() + "/" + word.getValue().word + "/" + word.getValue().count);
        }
        System.out.println("\n===============================");

        String unescapeJava = StringEscapeUtils.unescapeJava(stringWithUnicode);
        String normalizeString = Normalizer.normalize(unescapeJava, java.text.Normalizer.Form.NFKC);
        System.out.println("===>unescapeJava=" + unescapeJava + "|unescapeJava.length=" + unescapeJava.length() + "\n===>normalizeString=" + normalizeString + "|normalizeString.length=" + normalizeString.length());

    }

}
