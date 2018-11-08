package stackAndQueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution145 {

    class Command{
        int command;//0:表示继续遍历；1:表示加入集合
        TreeNode node;

        public Command(int command, TreeNode node) {
            this.command = command;
            this.node = node;
        }
    }

    private List<Integer> res=new ArrayList<>();
    //递归式
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root!=null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            res.add(root.val);
        }
        return res;
    }

    //迭代式
    public List<Integer> postorderTraversal1(TreeNode root){
        if (root==null){
            return res;
        }
        Stack<Command> stack=new Stack<>();
        stack.push(new Command(0,root));
        while (!stack.empty()){
            Command command = stack.pop();
            if (command.command==1){
                res.add(command.node.val);
            }else {
                stack.push(new Command(1,command.node));
                if (command.node.right!=null){
                    stack.push(new Command(0,command.node.right));
                }
                if (command.node.left!=null){
                    stack.push(new Command(0,command.node.left));
                }
            }
        }
        return res;

    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3=new TreeNode(3);
        root.left=n1;
        root.right=n2;
        n1.left=n3;
        List<Integer> res = postorderTraversal1(root);
        System.out.println(res);

    }
}
