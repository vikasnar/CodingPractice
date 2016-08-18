package vikas.stringss;

import java.util.Arrays;

/**
 * Created by VikasN on 8/17/16.
 * Problem Statement :
 *Given a smaller string S and a bigger string P, design an algorithm to find all permutations
 *of the shorter string within the longer one. Print the location of each permutation.
 *
 */
public class FindAllPermutationOfString {

    public static final int NO_OF_CHARACTER = 256;
    public static int[] count = new int[NO_OF_CHARACTER];

    public static void main (String... args){
        String S = "abbc";
        String P = "cbabadcbbabbcbabaabccbabc";
        findPermutations(S,P);
    }

    public static void findPermutations(String s, String p){
        int i = 0, len = p.length(), wlen = s.length();
        initializeCountArray(s);
        for (int j = 0; j < len - wlen+1; j++) {
            String sub = p.substring(j, j+wlen);
            if(areAnagram(s, sub))
                System.out.println(sub);
        }
    }

    public static void initializeCountArray(String s){
        Arrays.fill(count,0);
        for(int i = 0 ; i<s.length();i++){
            count[s.charAt(i)]++;
        }
    }

    public static boolean areAnagram(String s, String b){
        initializeCountArray(s);
        for(int i = 0 ; i < b.length();i++){
            if(count[b.charAt(i)] <= 0) return false;
            else
                count[b.charAt(i)]--;
        }
        return true;
    }

}
