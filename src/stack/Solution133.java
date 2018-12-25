package stack;

import org.junit.Test;

import java.util.*;

public class Solution133 {
    /**
     * Definition for undirected graph.
     *
     */
    class UndirectedGraphNode {
          int label;
          List<UndirectedGraphNode> neighbors;
          UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
      }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node==null){
            return null;
        }
        UndirectedGraphNode ret = new UndirectedGraphNode(node.label);
        //使用栈来遍历原始的结点
        Stack<UndirectedGraphNode> stack=new Stack<>();
        //K：原始结点；V：复制生成的新结点
        Map<UndirectedGraphNode,UndirectedGraphNode> nodeMap=new HashMap<>();
        stack.push(node);
        nodeMap.put(node,ret);
        while (!stack.empty()){
            UndirectedGraphNode cur = stack.pop();
            for (UndirectedGraphNode next:cur.neighbors){
                if (nodeMap.get(next)==null){
                    UndirectedGraphNode graphNode = new UndirectedGraphNode(next.label);
                    nodeMap.put(next,graphNode);
                    stack.push(next);
                }
                nodeMap.get(cur).neighbors.add(nodeMap.get(next));
            }

        }
        return ret;
    }
    @Test
    public void test(){
        UndirectedGraphNode node1 = new UndirectedGraphNode(0);
        UndirectedGraphNode node2 = new UndirectedGraphNode(0);
        UndirectedGraphNode node3 = new UndirectedGraphNode(0);

    }
}
