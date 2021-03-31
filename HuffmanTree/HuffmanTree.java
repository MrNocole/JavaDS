import java.util.*;

public class HuffmanTree {
    List<Map.Entry<Character,Integer>> info ;
    TreeNode head;
    PriorityQueue<TreeNode> priorityQueue;
    HuffmanTree(Map<Character,Integer> map){
        head = new TreeNode();
        info = new ArrayList<>(map.entrySet());
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
            System.out.println(node.c + " " + crt.toString());
            return;
        }
        dfs(node.left,new StringBuffer(crt+"0"));
        dfs(node.right,new StringBuffer(crt+"1"));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        StringDealer stringDealer = new StringDealer(s);
        HuffmanTree huffmanTree = new HuffmanTree(stringDealer.Deal());
        huffmanTree.DoHuffman();
        huffmanTree.dfs(huffmanTree.head,new StringBuffer());
    }
}
