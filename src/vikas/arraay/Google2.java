package vikas.arraay;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by VikasN on 9/25/16.
 */
public class Google2 {
    public static void main(String... args){
        String S =
                "dir1\n"+
                        " dir11\n"+
                        " dir12\n"+
                        "  picture.jpeg\n"+
                        "  dir121\n"+
                        "   file1.txt\n"+
                        "   file2.gif\n"+
                        "dir2\n"+
                        " file2.gif\n"+
                        " file3.gif\n"+
                        " file4.gif";

        String R =
                "dir1\n"+

                        " dir11\n"+
                        " dir12\n"+
                        "  picture.txt\n"+
                        "  dir121\n"+
                        "   file1.txt\n"+
                        "dir2\n"+
                        " file2.txt\n"+
                        "file1.png";

        String Q =
                "dir1\n"+

                        " dir11\n"+
                        " dir12\n"+
                        "  picture.jpeg\n"+
                        "  dir121\n"+
                        "   file1.txt\n"+
                        "   file2.gif\n"+
                        "dir2\n"+
                        " file2.gif\n"+
                        " file3.gif\n"+
                        " file4.gif";

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

        System.out.println(longestAbsolutePath(S)); // 18
        System.out.println(longestAbsolutePath(R)); // 11
        System.out.println(longestAbsolutePath(Q)); // 18
        System.out.println(longestAbsolutePath(P)); // 26
    }

    static int longestAbsolutePath(String s){
        int MAX = Integer.MIN_VALUE;
        Stack<String> path = new Stack<>();
        int pathLen = 0,spaceCount = -1, curSpaces;
        String[] input = s.split("\n");
//        path.push(input[0]);
//        pathLen = input[0].length()+1;
        for(int i =0;i<input.length;i++){
            String cur = input[i];
            curSpaces = cur.length() - cur.trim().length();
            if(curSpaces > spaceCount){
                spaceCount++;
                if(!isLeaf(cur)) pathLen += 1 + cur.trim().length();
                if(pathLen > MAX && isLeaf(cur) && isImage(cur)){

                    MAX = pathLen;
                }
                path.push(cur.trim());
            }
            else if(curSpaces == spaceCount){
                if(!isLeaf(path.peek()))
                    pathLen -= path.pop().length()+1;
                else {
                    if(pathLen > MAX && isImage(cur)){
                        MAX = pathLen;
                    }
                    path.pop();
                }
                path.push(cur.trim());
                if(!isLeaf(cur)) pathLen += 1 + cur.trim().length();
            }
            else{
                int diff = spaceCount - curSpaces + 1;
                spaceCount -= diff - 1 ;
                while(diff>0){
                    if(!isLeaf(path.peek()))
                        pathLen -= path.pop().length()+1;
                    else path.pop();
                    diff--;
                }
                path.push(cur.trim());
                if(!isLeaf(cur)) pathLen += 1 + cur.trim().length();
                if(pathLen > MAX && isImage(cur) && isLeaf(cur)){
                    MAX = pathLen;
                    if(pathLen == 0) MAX = 1;
                }
            }
        }
        if(MAX == Integer.MIN_VALUE) return 0;
        return MAX;
    }

    static boolean isLeaf(String node){
        return node.contains(".");
    }

    static boolean isImage(String node){
        return node.contains(".jpeg") || node.contains(".png") || node.contains(".gif");
    }

    static String printPath(Stack<String> stack){
        return (Arrays.toString(stack.toArray()));
    }
}
