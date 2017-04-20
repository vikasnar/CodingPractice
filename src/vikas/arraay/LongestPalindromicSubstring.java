package vikas.arraay;

/**
 * Created by VikasN on 9/13/16.
 */
public class LongestPalindromicSubstring {

    public static void main(String... args){
        LongestPalindromicSubstring loader = new LongestPalindromicSubstring();
        System.out.println(loader.longestPalindrome("banana"));
    }

    public String longestPalindrome(String signals) {
        if (signals.isEmpty()) {
            return null;
        }

        if (signals.length() == 1) {
            return signals;
        }

        String longestPalindrome = signals.substring(0, 1);
        for (int i = 0; i < signals.length(); i++) {
            String tmp = getCenter(signals, i, i);
            if (tmp.length() > longestPalindrome.length()) {
                longestPalindrome = tmp;
            }

            tmp = getCenter(signals, i, i + 1);
            if (tmp.length() > longestPalindrome.length()) {
                longestPalindrome = tmp;
            }
        }

        return longestPalindrome;
    }

    public String getCenter(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

}

