package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution173 {
    class BSTIterator{
        private List<Integer> data;
        private int nextIndex;

        public BSTIterator(TreeNode root) {
            data=new ArrayList<>();
            inOrder(root);

            nextIndex=0;

        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return nextIndex<data.size();

        }

        /** @return the next smallest number */
        public int next() {
            return data.get(nextIndex++);

        }

        private void inOrder(TreeNode root){
            if (root==null){
                return;
            }
            inOrder(root.left);
            data.add(root.val);
            inOrder(root.right);
        }

    }

    class BSTIterator1{


        private Stack<TreeNode> stack;
        public BSTIterator1(TreeNode root) {
            stack=new Stack<>();
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode cur = stack.pop();
            int res=cur.val;
            cur=cur.right;
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            return res;
        }



    }
}
