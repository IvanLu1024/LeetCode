package utils;

import javafx.util.Pair;
import tree.TreeNode;

import java.util.*;

public class TreeNodeUtils {
    /**
     * 根据前序遍历和中序遍历创建二叉树
     */
    public static TreeNode ConstructBinaryTree(int [] pre,int [] in) {
        return ConstructBinaryTree(pre,in,0,pre.length-1,0,in.length-1);
    }

    private static TreeNode ConstructBinaryTree(int [] pre,int [] in,int preStart,int preEnd,int inStart,int inEnd){
        if(preStart>preEnd || inStart>inEnd){
            return null;
        }
        TreeNode root=new TreeNode(pre[preStart]);
        //前序遍历的第一个元素就是根节点
        int find=0;
        //find就是根节点在中序遍历所得的数组中的位置
        for(int i=inStart;i<=inEnd;i++){
            if(in[i]==pre[preStart]) {
                find=i;
                break;
            }
        }
        //画图解决
        root.left=ConstructBinaryTree(pre,in,preStart+1,preStart+(find-inStart),inStart,find-1);
        root.right=ConstructBinaryTree(pre,in,preStart+(find-inStart)+1,preEnd,find+1,inEnd);
        return root;
    }

    /**
     * 根据中序遍历和后序遍历创建二叉树
     */
    public TreeNode reConstructBinaryTree(int [] in,int [] post) {
        return reConstructBinaryTree(in,post,0,in.length-1,0,post.length-1);
    }

    private TreeNode reConstructBinaryTree(int[] in,int[] post,int inStart,int inEnd,int postStart,int postEnd){
        if(inStart>inEnd || postStart>postEnd){
            return null;
        }
        TreeNode root=new TreeNode(post[postEnd]);
        int find=0;
        for(int i=inStart;i<=inEnd;i++){
            if(in[i]==post[postEnd]){
                find=i;
                break;
            }
        }
        root.left=reConstructBinaryTree(in,post,inStart,find-1,postStart,postStart+(find-inStart)-1);
        root.right=reConstructBinaryTree(in,post,find+1,inEnd,postStart+(find-inStart),postEnd-1);
        //因为最后一位是根节点，所以要减1
        return root;
    }

    private enum Command{GO,PRINT};
    private static class StackNode{
        Command command;
        TreeNode node;
        StackNode(Command command,TreeNode node){
            this.command=command;
            this.node=node;
        }
    }

    //前序遍历
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret=new ArrayList<>();
        if(root==null){
            return ret;
        }
        Stack<StackNode> stack=new Stack<>();
        stack.push(new StackNode(Command.GO,root));
        while(!stack.empty()){
            StackNode stackNode=stack.pop();
            Command command=stackNode.command;
            if(command== Command.PRINT){
                ret.add(stackNode.node.val);
            }else{
                if(stackNode.node.right!=null){
                    stack.push(new StackNode(Command.GO,stackNode.node.right));
                }
                if(stackNode.node.left!=null){
                    stack.push(new StackNode(Command.GO,stackNode.node.left));
                }
                stack.push(new StackNode(Command.PRINT,stackNode.node));
            }
        }
        return ret;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret=new ArrayList<>();
        if(root==null){
            return ret;
        }
        Stack<StackNode> stack=new Stack<>();
        stack.push(new StackNode(Command.GO,root));
        while(!stack.empty()){
            StackNode stackNode=stack.pop();
            Command command=stackNode.command;
            if(command== Command.PRINT){
                ret.add(stackNode.node.val);
            }else{
                if(stackNode.node.right!=null){
                    stack.push(new StackNode(Command.GO,stackNode.node.right));
                }
                stack.push(new StackNode(Command.PRINT,stackNode.node));
                if(stackNode.node.left!=null){
                    stack.push(new StackNode(Command.GO,stackNode.node.left));
                }
            }
        }
        return ret;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret=new ArrayList<>();
        if(root==null){
            return ret;
        }
        Stack<StackNode> stack=new Stack<>();
        stack.push(new StackNode(Command.GO,root));
        while(!stack.empty()){
            StackNode stackNode=stack.pop();
            Command command=stackNode.command;
            if(command== Command.PRINT){
                ret.add(stackNode.node.val);
            }else{
                stack.push(new StackNode(Command.PRINT,stackNode.node));
                if(stackNode.node.right!=null){
                    stack.push(new StackNode(Command.GO,stackNode.node.right));
                }
                if(stackNode.node.left!=null){
                    stack.push(new StackNode(Command.GO,stackNode.node.left));
                }
            }
        }
        return ret;
    }

    //层序遍历
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret=new ArrayList<>();
        if(root==null){
            return ret;
        }

        Queue<Pair<TreeNode,Integer>> queue=new LinkedList<>();
        queue.add(new Pair<TreeNode,Integer>(root,0));
        //root结点对应的是0层
        while(!queue.isEmpty()){
            Pair pair=queue.poll();
            TreeNode node= (TreeNode) pair.getKey();
            int level= (int) pair.getValue();

            if(level==ret.size()){
                //因为level是从0开始的，当level=ret.size()表示需要新创建 List，来存储level层的元素
                ret.add(new ArrayList<>());
            }
            //ret.get(level)表示的是level层
            ret.get(level).add(node.val);

            if(node.left!=null){
                queue.add(new Pair<TreeNode,Integer>(node.left,level+1));
            }
            if(node.right!=null){
                queue.add(new Pair<TreeNode,Integer>(node.right,level+1));
            }
        }
        return ret;
    }
}
