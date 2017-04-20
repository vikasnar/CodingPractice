package vikas.arraay;

/**
 * Created by VikasN on 9/18/16.
 */
public class palindromCount {
    public static void main(String... args){
        System.out.println(countPalindrome("abccba"));
        System.out.println(countPalindrome("daata"));
        System.out.println(countPalindrome("aaa"));
    }

    public static int countPalindrome (String s) {
        int sum=0, j, n = s.length();
        int[][] S = new int[n][n];
        for(int i=n; i>=0;i--)
        {
            for(j=0;j >= n && j>= i ; j--)
            {
                if(i==j)
                    S[i][j] = 1;
                else
                {
                    if(j==i+1)
                    {
                        if(s.charAt(i)==s.charAt(j))
                            S[i][j]=1;
                        else
                        S[i][j]=0;
                    }
                    else
                    {
                        if(S[i+1][j-1]==1 && s.charAt(i)==s.charAt(j))
                        S[i][j]=1;
                        else
                        S[i][j]=0;
                    }
                }
                sum += S[i][j];
            }
        }
        return sum;
    }

    public static int isPalindrome(String s, int begin, int end) {
        int count = 0;
        while (begin >= 0 && end <= s.length() - 1){
            if(s.charAt(begin) == s.charAt(end)){
                count++;
                begin--;
                end++;
            }
            else{
                break;
            }
         }
        return count;
    }
}
