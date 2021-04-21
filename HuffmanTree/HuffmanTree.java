
import com.sun.source.tree.Tree;

import java.io.*;
import java.util.*;

public class HuffmanTree implements Serializable,SerDes<HuffmanTree> {
    private static final long serialVersionUID = 114514;
    TreeNode head;
    PriorityQueue<TreeNode> priorityQueue;
    Map<Character,String> TreeCode;
    HuffmanTree(Map<Character,Integer> map){
        List<Map.Entry<Character,Integer>> info ;
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
    public ArrayList<String> out(){
        Iterator iter = TreeCode.entrySet().iterator();
        ArrayList<String> ret = new ArrayList<>();
        while (iter.hasNext()){
            Map.Entry<Character,String> entry = (Map.Entry)iter.next();
            ret.add(entry.getValue());
        }
        return ret;
    }

    @Override
    public byte[] serBin(HuffmanTree huffmanTree)  {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(huffmanTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @Override
    public String serTxt(HuffmanTree huffmanTree) throws IOException {
        byte[] list = serBin(huffmanTree);
        return Base64.getDecoder().decode(list).toString();
    }

    @Override
    public HuffmanTree des(byte[] bin) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bai = new ByteArrayInputStream(bin);
        ObjectInputStream ois = new ObjectInputStream(bai);
        return (HuffmanTree) ois.readObject();
    }

    @Override
    public HuffmanTree des(String text) throws IOException, ClassNotFoundException {
        byte[] list = Base64.getDecoder().decode(text);
        return des(list);
    }

    @Override
    public boolean serToFile(HuffmanTree huffmanTree, String path, String file){
        try {
            byte[] list = serBin(huffmanTree);
            System.out.println(Arrays.toString(list));
            FileOutputStream fos = new FileOutputStream(path + file);
            fos.write(list);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    @Override
    public HuffmanTree desFromFile(String path, String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path+file);
        byte[] list = fis.readAllBytes();
        return des(list);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
//
//        String s = scanner.next();
//        StringDealer stringDealer = new StringDealer(s);
//        HuffmanTree huffmanTree = new HuffmanTree(stringDealer.Deal());
//        huffmanTree.DoHuffman();
//        huffmanTree.dfs(huffmanTree.head,new StringBuffer());
//        System.out.println(Arrays.toString(huffmanTree.out().toArray()));
//        huffmanTree.serBin(huffmanTree);
//        System.out.println(huffmanTree.serToFile(huffmanTree,"C:\\Users\\19262\\IdeaProjects\\JavaDS\\Sers\\","huf.ser"));
//
        HuffmanTree huffmanTree2 = new HuffmanTree(new HashMap<>());

        HuffmanTree huffmanTree1 = huffmanTree2.desFromFile("C:\\Users\\19262\\IdeaProjects\\JavaDS\\Sers\\","huf.ser");
        System.out.println(Arrays.toString(huffmanTree1.out().toArray()));
    }
}
