package tree;

import java.util.ArrayList;
import java.util.List;

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
}
