import java.io.Serializable;

public class TreeNode implements Comparable<TreeNode>, Serializable {
    int val;
    char c;
    TreeNode left ,  right;
    TreeNode (){

    }
    TreeNode (builder builder){
        this.val = builder.val;
        this.left = builder.left;
        this.right = builder.right;
        this.c = builder.c;
    }
    public static class builder{
        int val;
        TreeNode left , right;
        char c;
        public builder(){}
        public builder setChar(char c){
            this.c = c;
            return this;
        }
        public builder setVal(int val){
            this.val = val;
            return this;
        }
        public builder setLeft(TreeNode left){
            this.left = left;
            return this;
        }
        public builder setRight(TreeNode right){
            this.right = right;
            return this;
        }
        public TreeNode build(){
            return new TreeNode(this);
        }
    }
    @Override
    public int compareTo(TreeNode o) {
        return this.val - o.val;
    }
}
