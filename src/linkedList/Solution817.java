package linkedList;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表（链表结点包含一个整型值）的头结点 head。
 *
 * 同时给定列表 G，该列表是上述链表中整型值的一个子集。
 *
 * 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。
 *
 * 示例 1：
 *
 * 输入:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * 输出: 2
 * 解释:
 * 链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 *
 * 输入:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * 输出: 2
 * 解释:
 * 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 * 注意:
 *
 * 如果 N 是给定链表 head 的长度，1 <= N <= 10000。
 * 链表中每个结点的值所在范围为 [0, N - 1]。
 * 1 <= G.length <= 10000
 * G 是链表中所有结点的值的一个子集.
 *
 */
public class Solution817 {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> gSet=new HashSet<>();
        for (int i = 0; i < G.length; i++) {
            gSet.add(G[i]);
        }
        ListNode cur=head;
        int count=0,curCount=0;
        //遍历链表
        while (cur!=null){
            //当G中包含当前结点的时候
            if (gSet.contains(cur.val)){
                //如果当前组件的长度为0，证明是一个新的组件，则组件的计数器加1
                if (curCount==0){
                    count++;
                }
                //统计当前组件的长度
                curCount++;
            }else {
                //如果G中不包含当前结点的时候，则将组件长度置为0
                curCount=0;
            }
            cur=cur.next;
        }
        return count;
    }
    @Test
    public void test(){
        int[] arr={0,1,2,3,4};
        int[] g={3, 0, 1, 4};
        ListNode head = LinkedListUtils.createLinkedList(arr, arr.length);
        int i = numComponents(head, g);
        System.out.println(i);
    }

}
