package vikas.arraay;

import java.util.ArrayList;

/**
 * Created by VikasN on 9/24/16.
 */
public class MissingWords {
    public static void main(String... args){
//        String s = "I am using hackerrank to improve my programming";
//        String r = "am hackerrank to improve my";
        String s = "I am GOD I am am";
        String r = "I I am";
        ArrayList<String> result = new ArrayList<>();
        String[] sWords = s.split(" ");
        String[] rWords = r.split(" ");
        int i =0,j =0;
        while(i<sWords.length && j<rWords.length){
            if(sWords[i].equals(rWords[j])){
                i++;
                j++;
            }
            else{
                result.add(sWords[i]);
                i++;
            }
        }
        while(i<sWords.length){
            result.add(sWords[i]);
            i++;
        }
        System.out.println(result.toString());
    }


}
