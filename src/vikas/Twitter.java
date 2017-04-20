package vikas;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by VikasN on 10/28/16.
 */
public class Twitter {
    //kraken
    public static void main(String... args){
        System.out.println(countPaths(5,5));
        System.out.println(decrypt("-Atvt hrqgse, Cnikg"));
//        System.out.println(decrypt("Bjj rwkcs dwpyp fwz ovors wxjs vje tcez fqg"));
//        System.out.println(decrypt("Li, ailu jw au facntll"));
        System.out.println(decrypt("Otjfvknou kskgnl, K mbxg iurtsvcnb ksgq hoz atv. Vje xcxtyqrl vt ujg smewfv vrmcxvtg rwqr ju vhm ytsf elwepuqyez. -Atvt hrqgse, Cnikg"));
    }

    public static int countPaths(int m, int n){
        int count[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            count[i][0] = 1;
        for (int j = 0; j < n; j++)
            count[0][j] = 1;
        for (int i = 1; i < m; i++)
        {
            for (int j = 1; j < n; j++)
                count[i][j] = count[i-1][j] + count[i][j-1] + count[i-1][j-1];
        }
        return count[m-1][n-1];
    }

    public static String decrypt(String encrypted_message){
        int[] KEY = new int[]{2,5,1,2,2,0,8};
        int KEY_Length = KEY.length;
        int messageLength = encrypted_message.length();
        int count = 0;
        for (int i = 0; i < messageLength; i++) {
            int asciiValue = (int)encrypted_message.charAt(i);
            if((asciiValue >= 65 && asciiValue <= 90)||(asciiValue >= 97 && asciiValue <= 122)){
                count ++;
            }
        }
        StringBuffer out = new StringBuffer();
        int i = 0, j = KEY_Length - ((count - 1) % KEY_Length);
        while(i < messageLength) {
            int asciiValue = (int)encrypted_message.charAt(i);
            // if it a Uppercase character
            if((asciiValue >= 65 && asciiValue <= 90)){
                asciiValue -= KEY[j];
                if(asciiValue < 65){
                    asciiValue = 90 - KEY[j] + 1;
                }
                out.append((char) asciiValue);
                i++;
                j++;
            } else if (asciiValue >= 97 && asciiValue <= 122){ // if it a lower character
                asciiValue -= KEY[j];
                if(asciiValue < 97){
                    asciiValue = 122 - KEY[j] + 1;
                }
                out.append((char) asciiValue);
                i++;
                j++;
            }else { // not a alphabet
                out.append((char) asciiValue);
                i++;
            }
            if(j == KEY_Length){ // reset Key
                j = 0;
            }
        }
        return out.toString();
    }

    static ArrayList<Integer> getKey(String encrypted_message){
        String SAMPLE = "-Your friend, Alice";
        ArrayList<Integer> key= new ArrayList<>();
        int SAMPLE_Length = SAMPLE.length(),i = 0, j =0;
        while(i < SAMPLE_Length){
            int asciiValue = (int)encrypted_message.charAt(i++);
            int sampleAsciiValue = (int)SAMPLE.charAt(j++);
            // if it a Uppercase character
            if((asciiValue >= 65 && asciiValue <= 90)){
                int diff = asciiValue - sampleAsciiValue;
                if(diff < 0){
                    diff = (65 - asciiValue) + (90 - sampleAsciiValue) + 1;
                }
                key.add(diff);
            } else if (asciiValue >= 97 && asciiValue <= 122){ // if it a lower character
                int diff = asciiValue - sampleAsciiValue;
                if(diff < 0){
                    diff = (97 - asciiValue) + (122 - sampleAsciiValue) + 1;
                }
                key.add(diff);
            }
        }
        return key;
    }
}
