package vikas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

class Predicate {
    boolean not;
    String name;
    String[] argumentList;
    boolean operator;
    boolean empty;
    boolean isNew;

    Predicate () {}

    Predicate(boolean empty) {
        this.empty = true;
        name = "";
        operator = false;
        not = false;
    }
}

class Clause {
    Predicate predicate;
    Clause left,right;
    HashSet<Integer> hashSet;
    ArrayList<Integer> pair;
    Clause() {
        hashSet = new HashSet<>();
        pair = new ArrayList<>();
    }
}

public class homework {

    public static void main(String[] args) {
        BufferedReader bufferedReader;
        int numberOfQueries;
        int numberOfSentences;
        ArrayList<String> queries = new ArrayList<>();
        ArrayList<String> sentences = new ArrayList<>();
        LogicProgrammer logicProgrammer = new LogicProgrammer();
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/VikasN/Personal/Code/CodingPractice/src/vikas/input.txt"));
            numberOfQueries = Integer.parseInt(bufferedReader.readLine());
            for(int i = 0; i < numberOfQueries; i++) {
                queries.add(bufferedReader.readLine());
            }
            numberOfSentences = Integer.parseInt(bufferedReader.readLine());
            for(int i = 0; i < numberOfSentences; i++) {
                sentences.add(bufferedReader.readLine());
            }
            logicProgrammer.parse(sentences, queries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LogicProgrammer {
    ArrayList<ArrayList<Clause>> postfix = new ArrayList<>();
    ArrayList<ArrayList<Clause>> newPostfix = new ArrayList<>();
    ArrayList<Clause> kb = new ArrayList<>();
    ArrayList<Clause> dirtyKB = new ArrayList<>();
    ArrayList<Clause> sentence = new ArrayList<>();
    Hashtable<String, String> hashTable = new Hashtable<>();
    ArrayList<String> hashKey = new ArrayList<>();
    int start='a';
    ArrayList<String> standardizeKey = new ArrayList<>();
    Hashtable<String, String> standardizeTable = new Hashtable<>();
    Clause queryClause = new Clause();
    PrintWriter printWriter;
    void parse(ArrayList<String> sentences, ArrayList<String> queries) {
        for (int i=0;i<sentences.size();i++) {
            parseSentence(sentences.get(i));
        }
        convertToCNF();
        createKB();
        standardizeKB();
        modifyKB();
        try {
            printWriter = new PrintWriter("output.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<queries.size();i++) {
            dirtyKB.clear();
            for (int a = 0; a < kb.size(); a++) {
                kb.get(a).pair.clear();
                kb.get(a).hashSet.clear();
                dirtyKB.add(kb.get(a));
            }
            addQuery(queries.get(i));
            int j,k;
            for (k = 0; k < dirtyKB.size(); k++) {
                j=0;
                while (j < dirtyKB.size()) {
                    if(k==j) {
                        j++;
                        continue;
                    }
                    if(dirtyKB.size()==1200) {
                        printWriter.write("FALSE\n");
                        break;
                    }
                    if(!dirtyKB.get(k).pair.contains(j)) {
                        if (resolveQuery(dirtyKB.get(k), dirtyKB.get(j))) {
                            printWriter.write("TRUE\n");
                            break;
                        }
                        dirtyKB.get(k).pair.add(j);
                        dirtyKB.get(j).pair.add(k);
                    }
                    j++;
                }
                if(j!=dirtyKB.size()) {
                    break;
                }
            }
            if (k == dirtyKB.size()) {
                printWriter.write("FALSE\n");
            }
        }
        printWriter.close();
        int k=0;
    }

    private void standardizeKB() {
        for(int i=0;i<kb.size();i++) {
            standardizeKey.clear();;
            standardizeTable.clear();
            traverse(kb.get(i));
        }
    }

    private void traverse(Clause clause) {
        if(clause.left!=null) {
            traverse(clause.left);
        }
        if(clause.right!=null) {
            traverse(clause.right);
        }
        if(clause.left==null && clause.right==null) {
            for(int i=0;i<clause.predicate.argumentList.length;i++) {
                if (!Character.isUpperCase(clause.predicate.argumentList[i].charAt(0))) {
                    if (standardizeKey.contains(clause.predicate.argumentList[i])) {
                        clause.predicate.argumentList[i] = standardizeTable.get(clause.predicate.argumentList[i]);
                    } else {
                        char a = (char) start;
                        standardizeKey.add(clause.predicate.argumentList[i]);
                        standardizeTable.put(clause.predicate.argumentList[i], Character.toString(a));
                        clause.predicate.argumentList[i] = Character.toString(a);
                        start++;
                    }
                }
            }
        }
    }

    private boolean resolveQuery(Clause clause1, Clause clause2) {
        ArrayList<Clause> clauseList1 = new ArrayList<>();
        ArrayList<Clause> clauseList2 = new ArrayList<>();
        getLeafNodes(clause1, clauseList1);
        getLeafNodes(clause2, clauseList2);
        for(int i=0;i<clauseList1.size();i++) {
            int j=0;
            while (j<clauseList2.size()) {
                if(clauseList1.get(i).predicate.name.equals(clauseList2.get(j).predicate.name)
                        && (clauseList1.get(i).predicate.not != clauseList2.get(j).predicate.not)) {
                    if(clause1.hashSet!=null && clause1.hashSet.contains(clause2.hashCode())) {
                        return false;
                    }
                    if(clause2.hashSet!=null && clause2.hashSet.contains(clause1.hashCode())) {
                        return false;
                    }
                    hashTable.clear();
                    hashKey.clear();
                    if(unify(clauseList1.get(i).predicate.argumentList, clauseList2.get(j).predicate.argumentList, hashTable)) {
                        if (clauseList1.size() == 1 && clauseList2.size() == 1) {
                            return true;
                        } else {
                            Clause c1 = copy(clause1);
                            Clause c2 = copy(clause2);
                            if(clauseList1.size()==1) {
                                c1=null;
                            } else {
                                c1 = deleteNode(c1, clauseList1.get(i));
                            }
                            if(clauseList2.size()==1) {
                                c2=null;
                            } else {
                                c2 = deleteNode(c2, clauseList2.get(j));
                            }
                            clauseList1.remove(i);
                            clauseList2.remove(j);
                            Predicate predicate = new Predicate();
                            predicate.name = "|";
                            predicate.operator = false;
                            predicate.argumentList = null;
                            predicate.not = false;
                            Clause clause = new Clause();
                            clause.predicate = predicate;
                            clause.left = c1;
                            clause.right = c2;
                            clause.hashSet.add(clause1.hashCode());
                            clause.hashSet.add(clause2.hashCode());
                            clause.predicate.isNew = true;
                            if(clause1.hashSet!=null) {
                                Iterator<Integer> it = clause1.hashSet.iterator();
                                while (it.hasNext()) {
                                    clause.hashSet.add(it.next());
                                }
                            }
                            if(clause2.hashSet!=null) {
                                Iterator<Integer> it = clause2.hashSet.iterator();
                                while (it.hasNext()) {
                                    clause.hashSet.add(it.next());
                                }
                            }
                            dirtyKB.add(clause);
                            return false;
                        }
                    }
                }
                j++;
            }
        }
        return false;
    }

    private Clause copy(Clause clause) {
        if(clause==null) {
            return clause;
        }
        Clause c = new Clause();
        Predicate predicate = new Predicate();
        predicate.name = clause.predicate.name;
        predicate.not = clause.predicate.not;
        if(clause.predicate.argumentList!=null) {
            predicate.argumentList = new String[clause.predicate.argumentList.length];
            for (int i = 0; i < clause.predicate.argumentList.length; i++) {
                predicate.argumentList[i] = clause.predicate.argumentList[i];
            }
        } else {
            predicate.argumentList=null;
        }
        predicate.operator = clause.predicate.operator;
        c.predicate = predicate;
        c.left = copy(clause.left);
        c.right = copy(clause.right);
        return c;
    }

    private Clause deleteNode(Clause clause, Clause deleteClause) {
        if (clause.left != null){
            clause.left = deleteNode(clause.left, deleteClause);
        }
        if (clause.right != null){
            clause.right = deleteNode(clause.right, deleteClause);
        }
        if(clause.left == null && clause.right==null) {
            if(clause.predicate.name.equals(deleteClause.predicate.name) && !clause.predicate.operator
                    && deleteClause.predicate.argumentList.length==clause.predicate.argumentList.length) {
                int i;
                for(i=0;i<deleteClause.predicate.argumentList.length;i++) {
                    if(!deleteClause.predicate.argumentList[i].equals(clause.predicate.argumentList[i])) {
                        break;
                    }
                }
                if(i==deleteClause.predicate.argumentList.length) {
                    return null;
                } else {
                    for(int j=0;j<clause.predicate.argumentList.length;j++) {
                        if(hashKey.contains(clause.predicate.argumentList[j])) {
                            clause.predicate.argumentList[j]= hashTable.get(clause.predicate.argumentList[j]);;
                        }
                    }
                }
            } else if (!clause.predicate.name.equals(deleteClause.predicate.name) && !clause.predicate.operator) {
                for(int i=0;i<clause.predicate.argumentList.length;i++) {
                    if(hashKey.contains(clause.predicate.argumentList[i])) {
                        clause.predicate.argumentList[i]= hashTable.get(clause.predicate.argumentList[i]);;
                    }
                }
            }
        }
        return clause;
    }

    private boolean unify(String[] args1, String[] args2, Hashtable<String, String> hashTable) {
        int i;
        if(args1!=null && args2!=null){
            if(args1.length!=args2.length) {
                return false;
            }
            for (i = 0; i < args1.length; i++) {
                if (Character.isUpperCase(args1[i].charAt(0)) && Character.isUpperCase(args2[i].charAt(0))) {
                    if (args1[i].equals(args2[i])) {

                    } else {
                        break;
                    }
                } else if (Character.isUpperCase(args1[i].charAt(0)) && !Character.isUpperCase(args2[i].charAt(0))) {
                    hashTable.put(args2[i], args1[i]);
                    hashKey.add(args2[i]);
                } else if (!Character.isUpperCase(args1[i].charAt(0)) && Character.isUpperCase(args2[i].charAt(0))) {
                    hashTable.put(args1[i], args2[i]);
                    hashKey.add(args1[i]);
                } else if (!Character.isUpperCase(args1[i].charAt(0)) && !Character.isUpperCase(args2[i].charAt(0))) {
                    hashTable.put(args1[i], args2[i]);
                    hashKey.add(args1[i]);
                }
            }
            if (i == args1.length) {
                return true;
            }
        }
        return false;
    }

    private void getLeafNodes(Clause clause, ArrayList<Clause> clauseList) {
        if (clause.left != null){
            getLeafNodes(clause.left, clauseList);
        }
        if (clause.right != null){
            getLeafNodes(clause.right, clauseList);
        }
        if(clause.left == null && clause.right==null && !clause.predicate.operator) {
            clauseList.add(clause);
        }
    }

    private  void addQuery(String query) {
        Predicate predicate = new Predicate();
        int i;
        if(query.charAt(1) == '~') {
            i = 2;
            predicate.not = false;
        } else {
            i = 0;
            predicate.not = true;
        }
        StringBuilder temp = new StringBuilder();
        StringBuilder tempArgs = new StringBuilder();
        String[] args;
        while (query.charAt(i)!= ' ' && query.charAt(i)!= '(') {
            temp.append(query.charAt(i));
            i++;
        }
        while(query.charAt(i)!= ')') {
            if(query.charAt(i)=='(' || query.charAt(i)==')' || query.charAt(i)==' ')
            {
            } else {
                tempArgs.append(query.charAt(i));
            }
            i++;
        }
        args = tempArgs.toString().split(",");
        predicate.name = temp.toString();
        predicate.argumentList = args;
        predicate.operator = false;
        queryClause.predicate = predicate;
        queryClause.left = null;
        queryClause.right = null;
        dirtyKB.add(0, queryClause);
        //dirtyKB.add(queryClause);
    }

    private void modifyKB() {
        for(int i=0;i<kb.size();i++) {
            splitTree(kb.get(i));
        }
    }

    private void createKB() {
        for(int i = 0;i<newPostfix.size();i++) {
            newPostfix.get(i).get(0).predicate.isNew=false;
            kb.add(newPostfix.get(i).get(0));
        }
    }

    private void splitTree(Clause clause) {
        if(clause.predicate.name.equals("&")){
            if(clause.left.predicate.name.equals("|") || !clause.left.predicate.operator) {
                kb.add(clause.left);
            }
            if (clause.right.predicate.name.equals("|") || !clause.right.predicate.operator) {
                kb.add(clause.right);
            }
            if (clause.left.predicate.name.equals("&")) {
                splitTree(clause.left);
            }
            if (clause.right.predicate.name.equals("&")) {
                splitTree(clause.left);
            }
            kb.remove(clause);
        }
    }

    private void convertToCNF() {
        for(int i=0;i<postfix.size();i++) {
            sentence = postfix.get(i);
            for (int j = 0; j < sentence.size(); j++) {
                if (!sentence.get(j).predicate.operator) {
                    continue;
                } else {
                    if (sentence.get(j).predicate.name.equals("~")) {
                        if(!sentence.get(j-1).predicate.operator) {
                            sentence.get(j - 1).predicate.not = !sentence.get(j - 1).predicate.not;
                            sentence.remove(j);
                            j = j - 1;
                        } else {
                            performOperation(sentence.get(j-1));
                            sentence.remove(j);
                            j= j -1;
                        }
                    } else if (isOperator(sentence.get(j).predicate.name.charAt(0))) {
                        sentence.get(j).right = sentence.get(j - 1);
                        sentence.get(j).left = sentence.get(j - 2);
                        performDistribution(sentence.get(j));
                        sentence.remove(j - 2);
                        sentence.remove(j - 2);
                        j=j-2;
                    } else if (isImplication(sentence.get(j).predicate.name.charAt(0))) {
                        sentence.get(j).predicate.name = "|";
                        sentence.get(j).right = sentence.get(j - 1);
                        sentence.get(j).left = sentence.get(j - 2);
                        performOperation(sentence.get(j).left);
                        performDistribution(sentence.get(j));
                        sentence.remove(j - 2);
                        sentence.remove(j - 2);
                        j=j-2;
                    }
                }
            }
            newPostfix.add(sentence);
        }

    }

    private void performDistribution(Clause clause) {
        if(clause.predicate.name.equals("|") && clause.left.predicate.name.equals("&")
                && !clause.right.predicate.operator) {
            clause.predicate.name="&";
            String tempBName = clause.left.right.predicate.name;
            boolean tempBNot = clause.left.right.predicate.not;
            String[] tempBargs = clause.left.right.predicate.argumentList;
            String tempCName = clause.right.predicate.name;
            boolean tempCNot = clause.right.predicate.not;
            String[] tempCargs = clause.right.predicate.argumentList;
            clause.left.right.predicate.name = tempCName;
            clause.left.right.predicate.not = tempCNot;
            clause.left.right.predicate.argumentList = tempCargs;
            clause.left.predicate.name = "|";
            clause.right.predicate.name = "|";
            clause.right.predicate.operator = true;
            clause.right.predicate.not = false;
            clause.right.left = new Clause();
            clause.right.left.predicate = new Predicate();
            clause.right.left.predicate.name = tempBName;
            clause.right.left.predicate.not = tempBNot;
            clause.right.left.predicate.argumentList = tempBargs;
            clause.right.left.left = null;
            clause.right.left.right = null;
            clause.right.right = new Clause();
            clause.right.right.predicate = new Predicate();
            clause.right.right.predicate.name = tempCName;
            clause.right.right.predicate.not = tempCNot;
            clause.right.right.predicate.argumentList = tempCargs;
            clause.right.right.left = null;
            clause.right.right.right = null;
        } else if(clause.predicate.name.equals("|") && clause.right.predicate.name.equals("&")
                && !clause.left.predicate.operator) {
            clause.predicate.name="&";
            String tempAName = clause.left.predicate.name;
            boolean tempANot = clause.left.predicate.not;
            String[] tempAargs = clause.left.predicate.argumentList;
            String tempBName = clause.right.left.predicate.name;
            boolean tempBNot = clause.right.left.predicate.not;
            String[] tempBargs = clause.right.left.predicate.argumentList;
            clause.right.left.predicate.name = tempAName;
            clause.right.left.predicate.not = tempANot;
            clause.right.left.predicate.argumentList = tempAargs;
            clause.left.predicate.name = "|";
            clause.right.predicate.name = "|";
            clause.left.predicate.operator = true;
            clause.left.predicate.not = false;
            clause.left.left = new Clause();
            clause.left.left.predicate = new Predicate();
            clause.left.left.predicate.name = tempAName;
            clause.left.left.predicate.not = tempANot;
            clause.left.left.predicate.argumentList = tempAargs;
            clause.left.left.left = null;
            clause.left.left.right = null;
            clause.left.right = new Clause();
            clause.left.right.predicate = new Predicate();
            clause.left.right.predicate.name = tempBName;
            clause.left.right.predicate.not = tempBNot;
            clause.left.right.predicate.argumentList = tempBargs;
            clause.left.right.left = null;
            clause.left.right.right = null;
        }
    }

    private void performOperation(Clause clause) {
        if(!clause.predicate.operator) {
            clause.predicate.not = !clause.predicate.not;
        } else if (clause.predicate.name.equals("&")) {
            clause.predicate.name = "|";
            performOperation(clause.left);
            performOperation(clause.right);
        } else if (clause.predicate.name.equals("|")) {
            clause.predicate.name = "&";
            performOperation(clause.left);
            performOperation(clause.right);
        }
    }

    private void parseSentence(String sentence) {
        Stack<Character> stack = new Stack<>();
        ArrayList<Clause> pred = new ArrayList<>();
        for(int i=0;i<sentence.length();i++) {
            char c = sentence.charAt(i);
            if(c==' ') {
                continue;
            }  else if(isOperator(c) || isOpeningBracket(c)) {
                stack.push(c);
            } else if (isImplication(c)) {
                stack.push(c);
                i++;
            } else if(Character.isUpperCase(c)) {
                StringBuilder temp = new StringBuilder();
                StringBuilder tempArgs = new StringBuilder();
                String[] args;
                while (sentence.charAt(i)!= ' ' && sentence.charAt(i)!= '(') {
                    temp.append(sentence.charAt(i));
                    i++;
                }
                while(sentence.charAt(i)!= ')') {
                    if(sentence.charAt(i)=='(' || sentence.charAt(i)==')' || sentence.charAt(i)==' ')
                    {
                    } else {
                        tempArgs.append(sentence.charAt(i));
                    }
                    i++;
                }
                args = tempArgs.toString().split(",");
                Predicate predicate = new Predicate();
                predicate.name = temp.toString();
                predicate.argumentList = args;
                predicate.not = false;
                predicate.operator = false;
                Clause clause = new Clause();
                clause.predicate = predicate;
                clause.left = null;
                clause.right = null;
                pred.add(clause);
            } else if (c == ')' && !stack.isEmpty()) {
                while (stack.peek()!='(') {
                    Predicate predicate = new Predicate();
                    predicate.operator = true;
                    predicate.not = false;
                    predicate.name = stack.pop().toString();
                    Clause clause = new Clause();
                    clause.predicate = predicate;
                    clause.left = null;
                    clause.right = null;
                    pred.add(clause);
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            Predicate predicate = new Predicate();
            predicate.operator = true;
            predicate.not = false;
            predicate.name = stack.pop().toString();
            Clause clause = new Clause();
            clause.predicate = predicate;
            clause.left = null;
            clause.right = null;
            pred.add(clause);
        }
        postfix.add(pred);

    }

    private boolean isOperator(char c) {
        if (c == '&' || c == '|' || c == '~') {
            return true;
        }
        return false;
    }

    private boolean isOpeningBracket(char c) {
        if (c=='(') {
            return true;
        }
        return false;
    }

    private boolean isImplication(char c) {
        if (c == '=') {
            return true;
        }
        return false;
    }
}