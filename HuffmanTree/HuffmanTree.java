import com.sun.source.tree.Tree;

import javax.swing.*;
import java.util.*;

public class HuffmanTree {
    List<Map.Entry<Character,Integer>> info ;
    TreeNode head;
    PriorityQueue<TreeNode> priorityQueue;
    Map<Character,String> TreeCode;
    HuffmanTree(Map<Character,Integer> map){
        head = new TreeNode();
        info = new ArrayList<>(map.entrySet());
        TreeCode = new HashMap<>();
        Collections.sort(info, new Comparator<>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character,Integer> entry : info){
            TreeNode treeNode = new TreeNode.builder().setVal(entry.getValue()).setChar(entry.getKey()).build();
            priorityQueue.offer(treeNode);
        }
    }

    public void DoHuffman(){
        while (priorityQueue.size()>1){
            TreeNode l = priorityQueue.poll();
            TreeNode r = priorityQueue.poll();
            TreeNode tmp =  new TreeNode.builder().setVal(l.val+r.val).setLeft(l).setRight(r).build();
            priorityQueue.offer(tmp);
        }
        head = priorityQueue.poll();
    }

    public void dfs(TreeNode node, StringBuffer crt){
        if (node.left==null && node.right==null) {
            TreeCode.put(node.c,crt.toString());
//            System.out.println(node.c + " " + crt.toString());
            return;
        }
        dfs(node.left,new StringBuffer(crt+"0"));
        dfs(node.right,new StringBuffer(crt+"1"));
    }

    public String GetCodeByChar (char c){
        return TreeCode.getOrDefault(c, null);
    }
    public Character DfsGetChar (String code , int idx , TreeNode node){
        if (node.left == null && node.right == null){
            return node.c;
        }
        return DfsGetChar(code,idx+1,(code.charAt(idx)=='1')?node.right:node.left);
    }
    public Character GetCharByCode (String code){
        if (TreeCode.containsValue(code)){
            return DfsGetChar(code,0,this.head);
        } else {
            return null;
        }
    }
    public ArrayList<String> codingString (String string){
        ArrayList<String> ret = new ArrayList<>();
        for (int i = 0 ; i < string.length() ; i ++){
            ret.add(GetCodeByChar(string.charAt(i)));
        }
        return ret;
    }
    public String decoding (String string){
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0 ; i < string.length() ; ){
            TreeNode node = head;
            while (i < string.length()){
                if (string.charAt(i)=='1'&&node.right==null)break;
                if (string.charAt(i)=='0'&&node.left==null)break;
                node = (string.charAt(i)=='1')?node.right:node.left;
                i++;
            }
            stringbuffer.append(node.c);
        }
        return stringbuffer.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        StringDealer stringDealer = new StringDealer(s);
        HuffmanTree huffmanTree = new HuffmanTree(stringDealer.Deal());
        huffmanTree.DoHuffman();
        huffmanTree.dfs(huffmanTree.head,new StringBuffer());
        System.out.println("Coding Success!\nLet's coding a String:");
        String string = scanner.next();
        ArrayList<String> to = huffmanTree.codingString(string);
        for (String s1 : to){
            System.out.print(s1+" ");
        }
        System.out.println("\nLet's decoding a String:");
        string = scanner.next();
        System.out.println(huffmanTree.decoding(string));
    }
}
