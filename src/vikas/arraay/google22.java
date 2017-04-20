package vikas.arraay;

import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

/* given a directory structure like below find the length of the maximum absolute path of an image file from start*/
public class google22 {
    public static void main(String args[]){
        int answer = 0;
        StringBuffer buffer = new StringBuffer();
        Stack<String> stack = new Stack<>();
        int last = 0;
            /*String S = "dir1\n" +
                    " dir11\n" +
                    " dir12\n" +
                    "  picture.jpeg\n" +
                    "  dir121\n" +
                    "  file1.txt\n" +
                    "dir2\n" +
                    " file2.gif";
            String stringArray[] = S.split("\\n");*/

        String P =
                "dir1\n"+

                        " dir11\n"+
                        " dir12\n"+
                        "  picture.jpeg\n"+
                        "  dir121\n"+
                        "   file1.txt\n"+
                        "   file2.gif\n"+
                        "   file2.txt\n"+
                        "   file3.gif\n"+
                        "dir2\n"+
                        " file2.gif\n"+
                        " file3.gif\n"+
                        " file4.gif\n"+
                        " dir21\n"+
                        "  picture.jpeg\n"+
                        "  dir221\n"+
                        "   file11.txt\n"+
                        "   file22.gif\n"+
                        "   dir2221\n"+
                        "    file111.txt\n"+
                        "    file222.gif\n";
        try {
            String[] input = P.split("\n");
            String current;
            for(int j =1;j<input.length;j++) {
                current = input[j];
                //for (String current : stringArray) {
                int i = 0;
                while (current.charAt(i) == ' ') {
                    i++;
                }
                current = current.replaceAll("\\s+", "");
                if (last == i && i != 0) {
                    stack.pop();
                } else if (i < last) {
                    while (i != last) {
                        stack.pop();
                        --last;
                    }
                }
                stack.push('/' + current);
                if (current.contains(".jpeg") || current.contains(".png") || current.contains(".gif")) {
                    for (String s : stack) {
                        buffer.append(s);
                        answer += s.length();
                    }
                    System.out.println(buffer.toString());
                    buffer = new StringBuffer();
                    stack.pop();
                } else if (current.contains(".")) {
                    stack.pop();
                } else {
                    last = i;
                }
            }
        }
        catch (Exception e){
            System.out.println("exception");
        }
        System.out.println(answer);
    }
}
