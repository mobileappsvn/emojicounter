package com.mobileappsvn.emojicounter;

import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.EmojiSpan;
import android.text.Spannable;
import android.util.Log;

import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiUtil {
    private final static String sEmojiRegex = "(?:[\\u2700-\\u27bf]|[\\u2197-\\ufe0f]|[\\u2196\\uFE0F]|[\\u2195]|[\\u2194\\uFE0F]|[\\uD83D\\uDD04\\uD83D\\uDD19]|[(\\u203C|\\u2049|\\u00A9|\\u00AE|\\u2122|\\u2139)\\uFE0F]|"
            + "(\\uD83C\\uDFF4\\uDB40\\uDC67\\uDB40\\uDC62\\uDB40)(\\uDC77\\uDB40\\uDC6C\\uDB40\\uDC73\\uDB40\\uDC7F|\\uDC73\\uDB40\\uDC63\\uDB40\\uDC74\\uDB40\\uDC7F|\\uDC65\\uDB40\\uDC6E\\uDB40\\uDC67\\uDB40\\uDC7F)|"

            /*+ "(?:[\\uD83C\\uDFF4\\uDB40\\uDC67\\uDB40\\uDC62\\uDB40\\uDC77\\uDB40\\uDC6C\\uDB40\\uDC73\\uDB40\\uDC7F]){7}|"
            + "(?:[\\uD83C\\uDFF4\\uDB40\\uDC67\\uDB40\\uDC62\\uDB40\\uDC73\\uDB40\\uDC63\\uDB40\\uDC74\\uDB40\\uDC7F]){7}|"
            + "(?:[\\uD83C\\uDFF4\\uDB40\\uDC67\\uDB40\\uDC62\\uDB40\\uDC65\\uDB40\\uDC6E\\uDB40\\uDC67\\uDB40\\uDC7F]){7}|"*/
            + "(?:[\\ud83c\\udde6-\\ud83c\\uddff]){2}|"
            + "[\\ud800\\udc00-\\uDBFF\\uDFFF]|[\\u2600-\\u26FF])[\\ufe0e\\ufe0f]?(?:[\\u0300-\\u036f\\ufe20-\\ufe23\\u20d0-\\u20f0]|[\\ud83c\\udffb-\\ud83c\\udfff])?"
            + "(?:\\u200d(?:[^\\ud800-\\udfff]|"
            + "(?:[\\ud83c\\udde6-\\ud83c\\uddff]){2}|"
            + "[\\ud800\\udc00-\\uDBFF\\uDFFF]|[\\u2600-\\u26FF])[\\ufe0e\\ufe0f]?(?:[\\u0300-\\u036f\\ufe20-\\ufe23\\u20d0-\\u20f0]|[\\ud83c\\udffb-\\ud83c\\udfff])?)*|"
            + "[\\u0023-\\u0039]\\ufe0f?\\u20e3|\\u3299|\\u3297|\\u303d|\\u3030|\\u24c2|[\\ud83c\\udd70-\\ud83c\\udd71]|[\\ud83c\\udd7e-\\ud83c\\udd7f]|\\ud83c\\udd8e|"
            + "[\\ud83c\\udd91-\\ud83c\\udd9a]|[\\ud83c\\udde6-\\ud83c\\uddff]|[\\ud83c\\ude01-\\ud83c\\ude02]|\\ud83c\\ude1a|\\ud83c\\ude2f|[\\ud83c\\ude32-\\ud83c\\ude3a]|"
            + "[\\ud83c\\ude50-\\ud83c\\ude51]|\\u203c|\\u2049|[\\u25aa-\\u25ab]|\\u25b6|\\u25c0|[\\u25fb-\\u25fe]|\\u00a9|\\u00ae|\\u2122|\\u2139|\\ud83c\\udc04|"
            + "[\\u2600-\\u26FF]|\\u2b05|\\u2b06|\\u2b07|\\u2b1b|\\u2b1c|\\u2b50|\\u2b55|\\u231a|\\u231b|\\u2328|\\u23cf|[\\u23e9-\\u23f3]|[\\u23f8-\\u23fa]|\\ud83c\\udccf|\\u2934|\\u2935|[\\u2190-\\u21ff]";

    private final static String subStringRegex = sEmojiRegex +
            "|[\\x{3041}-\\x{3096}]" +//\p{Hiragana}
            "|[\\x{30A0}-\\x{30FF}]" +//\p{Katakana}
            "|[\\x3400-\\x4DB5\\x4E00-\\x9FCB\\xF900-\\xFA6A]" +//\p{Han}
            "|[\\x{2E80}-\\x{2FD5}]" +//\p{Han}
            "|[\\xFF5F-\\xFF9F]" +
            "|[\\x3000-\\x303F]" +
            "|[\\x31F0-\\x31FF\\x3220-\\x3243\\x3280-\\x337F]" +
            "|[\\x{FF61}-\\x{FF9F}]" +
            "|.";//\d|\w|\s

    public static int countEmojis(String txt) {
        final Pattern pattern = Pattern.compile(sEmojiRegex);
        final Matcher matcher = pattern.matcher(txt);
        int foundEmojiCount = 0;
        while (matcher.find()) {
            //System.out.println("Full match=" + matcher.group(0));
            /*char[] charArray = matcher.group(0).toCharArray();
            for (char ej: charArray) {
                System.out.printf("\\u%04X", (int)ej);
            }
            System.out.println("\n----------END------------\n");*/
            foundEmojiCount++;
        }
        return foundEmojiCount;
    }

    public static StringBuilder fillUpGraduallyHandler(String txt, int charCount) {
        System.out.println("\n\n=============Fill Up Gradually Handler=============\n\n");

        Log.e("MainActivity", "=========>txt=" + txt + "\n\n");

        StringBuilder outStringBuff = new StringBuilder();

        if (isEmptyOrNull(txt)) return outStringBuff;


        Pattern pattern = Pattern.compile(subStringRegex);
        Matcher matcher = pattern.matcher(nullToEmpty(txt));

        int foundEmojiCount = 0;
        while (matcher.find()) {
            foundEmojiCount++;

            // Get the group's captured text
            if (!isEmptyOrNull(matcher.group(0))) {
                Log.e("MainActivity","=>groupCount()=" + matcher.groupCount() +"|groupStr" + foundEmojiCount + "=" + matcher.group(0));
                if (foundEmojiCount > charCount) break;
                outStringBuff.append(matcher.group(0));

                //Maybe the last character at the charCount position is Fullsize of the Japanese need to take
                if (foundEmojiCount == charCount) {
                    String lastChar = nullToEmpty(matcher.group(0));
                    if (matcher.find()) {
                        if (Normalizer.normalize(nullToEmpty(lastChar + matcher.group(0)), java.text.Normalizer.Form.NFKC).length() == 1) {
                            Log.e("MainActivity","=>groupCount()=" + matcher.groupCount() +"|groupStr" + foundEmojiCount + "=" + matcher.group(0));
                            outStringBuff.append(matcher.group(0));
                        }
                    }
                }
            }

        }
        System.out.println("\n\n======>outStringBuff=" + outStringBuff.toString() + "|length=" + outStringBuff.toString().length() + "|length after normalized=" + countTextWithEmoji(outStringBuff.toString()));
        System.out.println("\n\n=============**********=============\n\n");

        return outStringBuff;
    }

    public static String subString(String templateStr, final int charCount) {
        //Cant not use the recursion algorithm to resolve this issue
        if (isEmptyOrNull(templateStr)) return "";

        StringBuilder stringBuffOutput = new StringBuilder();
        int charNumberOutput = 0;
        int charCountNeedTake = charCount;

        //While not enough number characters to take.
        while (/*charNumberOutput < charCount*/ charCountNeedTake > 0) {

            StringBuilder stringBuffTemp = fillUpGraduallyHandler(templateStr, charCountNeedTake);
            int justNumberSubString = countTextWithEmoji(stringBuffTemp.toString());

            charNumberOutput += justNumberSubString;
            charCountNeedTake = charCount - charNumberOutput;

            if (charNumberOutput <= charCount) {
                stringBuffOutput.append(stringBuffTemp);
                templateStr = templateStr.replaceAll(stringBuffTemp.toString(), "");
            }
            System.out.println("\n---->charCount=" + charCount + "|charNumberOutput=" + charNumberOutput + "|justNumberSubString=" + justNumberSubString + "|charCountNeedTake=" + charCountNeedTake + "|stringBuffTemp=" + stringBuffTemp.toString());
            //Original String not enough to take
            if (justNumberSubString == 0) break;
        }

        return stringBuffOutput.toString();
    }

    /**
     * Count Emoji & text character number. Available with JDK 8 or higher
     * @param value
     * @return
     */
    public static int getGraphemeLength(String value) {
        BreakIterator it = BreakIterator.getCharacterInstance();
        it.setText(value);
        int count = 0;
        while (it.next() != BreakIterator.DONE) {
            count++;
        }
        return count;
    }


    public static int countTextWithEmoji(String txt) {
        if (txt == null || txt.isEmpty()) {
            return 0;
        }
        final String str = removeAllEmojis(txt);
        //return str.length() + countEmojis(txt);
        return Normalizer.normalize(str, java.text.Normalizer.Form.NFKC).length() + countEmojis(txt);
    }

    public static boolean isEmoji(String code) {
        return countEmojis(code) != 0;
    }

    public static String removeAllEmojis(String emojiText) {
        final Pattern pattern = Pattern.compile(sEmojiRegex);
        final Matcher matcher = pattern.matcher(emojiText);
        final String result = matcher.replaceAll("");
        return result.trim();
    }

    public static int getEmojiCount(CharSequence charSequence) {
        String textAndEmoji = nullToEmpty(charSequence);
        Log.w("MainActivity", "==>getEmojiCount().charSequence=" + charSequence.toString() + "|length=" + textAndEmoji.length() + "|getGraphemeLength=" + getGraphemeLength(textAndEmoji));

        char[] charArray = textAndEmoji.toCharArray();
        for (char ej: charArray) {
            System.out.printf("\\u%04X", (int)ej);
        }
        System.out.println("\n\n========================\n");


        /*Set<String> emojiList = new HashSet<>();
        CharSequence processed = EmojiCompat.get().process(charSequence, 0, charSequence.length() -1, Integer.MAX_VALUE, EmojiCompat.REPLACE_STRATEGY_ALL);
        if (processed instanceof Spannable) {
            Spannable spannable = (Spannable) processed;

            EmojiSpan[] emojiSpans = spannable.getSpans(0, spannable.length() - 1, EmojiSpan.class);
            for (EmojiSpan emojiSpan : emojiSpans) {
                int spanStart = spannable.getSpanStart(emojiSpan);
                int spanEnd = spannable.getSpanEnd(emojiSpan);
                CharSequence emojiCharSequence = spannable.subSequence(spanStart, spanEnd);
                emojiList.add(String.valueOf(emojiCharSequence));
            }
        }

        return emojiList.size();*/

        int count = 0;
        CharSequence processed = EmojiCompat.get().process(charSequence, 0, charSequence.length() -1, Integer.MAX_VALUE, EmojiCompat.REPLACE_STRATEGY_ALL);
        if (processed instanceof Spannable) {
            Spannable spannable = (Spannable) processed;
            count = spannable.getSpans(0, spannable.length() - 1, EmojiSpan.class).length;
        }
        return count;
    }

//    public static int getEmojiCount(CharSequence charSequence) {
//        Log.w("MainActivity", charSequence.toString());
//
//
//        int emojiCount = 0;
//
//        for (int i = 0; i < charSequence.toString().length(); i++) {
//            int type = Character.getType(charSequence.toString().charAt(i));
//
//            Log.w("MainActivity", "type=" + type);
//            if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
//                emojiCount++;
//            }
//        }
//
//        return emojiCount/2;
//    }

//    public static int getEmojiCount(CharSequence charSequence) {
//        LogUtils.w(TAG, charSequence.toString());
//        int count = 0;
//        CharSequence processed = EmojiCompat.get().process(charSequence, 0, charSequence.length() -1, Integer.MAX_VALUE, EmojiCompat.REPLACE_STRATEGY_ALL);
//        if (processed instanceof Spannable) {
//            Spannable spannable = (Spannable) processed;
//            count = spannable.getSpans(0, spannable.length() - 1, EmojiSpan.class).length;
//        }
//        return count;
//    }

    /**
     * Checks if is empty or null.
     *
     * @param input the input
     * @return true, if is empty or null
     */
    public static boolean isEmptyOrNull(String input) {
        if (input == null) return true;
        if (input.trim().length() == 0 || "null".equalsIgnoreCase(input.trim())) return true;
        return false;
    }

    /**
     * Checks if is empty or null.
     *
     * @param input the object
     * @return true, if is empty or null
     */
    public static boolean isEmptyOrNull(Object input) {
        if (input == null) return true;
        if (nullToEmpty(input).trim().length() == 0) return true;
        return false;
    }

    public static String nullToEmpty(Object input) {
        return (input == null ? "" : ("null".equals(input) ? "" : input.toString()));
    }
}