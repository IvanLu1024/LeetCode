package tree;

import org.junit.Test;

import java.util.*;

public class Solution863 {

    private Map<Integer,Set<Integer>> graph=new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        buildGraph(null,root);
        List<Integer> res=new ArrayList<>();
        Queue<Integer> queue=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        int distance=0;
        ((LinkedList<Integer>) queue).push(target.val);
        visited.add(target.val);
        while (!queue.isEmpty()){
            int size = queue.size();
            //遍历当前层
            for (int i = 0; i < size; i++) {
                Integer cur = queue.poll();
                if (distance==K){
                    res.add(cur);
                }
                if (graph.containsKey(cur)){
                    for (Integer neighbor:graph.get(cur)){
                        if (!visited.contains(neighbor)){
                            ((LinkedList<Integer>) queue).offer(neighbor);
                            visited.add(neighbor);
                        }
                    }

                }
            }
            distance++;
        }
        return res;

    }

    //将二叉树构建成一个无向图
    private void buildGraph(TreeNode parent,TreeNode child){
        if (parent!=null){
            if (graph.get(parent.val)==null){
                LinkedHashSet<Integer> pSet = new LinkedHashSet<Integer>();
                pSet.add(child.val);
                graph.put(parent.val,pSet);
            }else {
                graph.get(parent.val).add(child.val);
            }
            if (graph.get(child.val)==null){
                LinkedHashSet<Integer> cSet = new LinkedHashSet<Integer>();
                cSet.add(parent.val);
                graph.put(child.val,cSet);
            }else {
                graph.get(child.val).add(parent.val);
            }

        }
        if (child.left!=null){
            buildGraph(child,child.left);
        }
        if (child.right!=null){
            buildGraph(child,child.right);
        }
    }

    @Test
    public void test(){
        int[] in={6,5,7,2,4,3,0,1,8};
        int[] pre={3,5,6,2,7,4,1,0,8};
        TreeNode root = TreeNodeUtils.ConstructBinaryTree(pre, in);
        TreeNode target=root.left;
        int k=2;
        List<Integer> list = distanceK(root, target, k);
        System.out.println(list);


    }
}
