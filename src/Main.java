import linkedList.LinkedListUtils;
import linkedList.ListNode;
import org.junit.Test;
import tree.TreeNode;
import tree.TreeNodeUtils;
import utils.ArrayUtils;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class Main {

    static int a=1;

    /*static {
        a=2;
        b=4;
        System.out.println(a);
    }*/

    static int b=2;


    public void quickSort(int[] arr,int l ,int h){
        if (l>=h){
            return;
        }
        int mid = partition(arr,l,h);
        quickSort(arr,l,mid-1);
        quickSort(arr,mid+1,h);
    }

    private int partition(int[] arr, int l, int h) {
        int piovt=arr[l];
        while (l<h){
            while (l<h&&arr[h]>piovt)   h--;
            arr[l]=arr[h];
            while (l<h&&arr[l]<piovt)   l++;
            arr[h]=arr[l];
        }
        arr[l]=piovt;
        return l;
    }
    private void swap(int[] arr,int i,int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


    /**
     * 示例 1：

     输入: "the sky is blue"
     输出: "blue is sky the"
     示例 2：

     输入: "  hello world!  "
     输出: "world! hello"
     解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if (s==null||s.length()==0){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        String[] strs = s.trim().split("\\s");
        for (int i = strs.length-1; i >=0; i--) {
            if (strs[i].length()==0){
                continue;
            }
            if (i!=0){
                sb.append(strs[i].trim()+" ");
            }else {
                sb.append(strs[i].trim());
            }
        }
        return sb.toString();
    }

    private List<String> res=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length()<4||s.length()>12){
            return res;
        }
        generateIp(s,"",0);
        return res;
    }
    private void generateIp(String s,String temp,int n){
        if (s.length()>3*(4-n)){
            return;
        }
        if (n==4&&s.length()==0){
            res.add(temp.substring(0,temp.length()-1));
        }

        for (int i = 1; i < 4; i++) {
            if (s.length()<i){
                break;
            }
            if (isValid(s.substring(0,i))){
                generateIp(s.substring(i),temp+s.substring(0,i)+".",n+1);
            }
        }

    }

    private boolean isValid(String s){
        if (s.charAt(0)=='0'){
            return s.equals("0");
        }
        int i = Integer.parseInt(s);
        if (i>=0&&i<=255){
            return true;
        }else {
            return false;
        }
    }


    class LRUCache {

        class Node{
            int key;
            int value;
            Node pre;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
        private Map<Integer,Node> map;
        private Node head;  //最近使用的节点
        private Node tail;  //最久使用的节点
        private int count;  //当前存储节点的数量
        private int capacity;   //容量

        public LRUCache(int capacity) {
            map=new HashMap<>();
            head=new Node(0,0);
            tail=new Node(0,0);
            head.next=tail;
            head.pre=null;
            tail.pre=head;
            tail.next=null;
            this.count=0;
            this.capacity=capacity;
        }

        // - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1
        public int get(int key) {
            if (map.containsKey(key)){
                Node node = map.get(key);
                int res = node.value;
                removeNode(node);
                move2Head(node);
                return res;
            }else {
                return -1;
            }
        }

        // 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
        public void put(int key, int value) {
            if (map.containsKey(key)){
                Node node = map.get(key);
                node.value=value;
                removeNode(node);
                move2Head(node);
            }else {
                Node node = new Node(key, value);
                map.put(key,node);
                move2Head(node);
                if (count>=capacity){
                    map.remove(tail.pre.key);
                    removeNode(tail.pre);
                }else {
                    count++;
                }
            }
        }
        private void move2Head(Node node){
            node.next=head.next;
            head.next.pre=node;
            head.next=node;
            node.pre=head;
        }

        private void removeNode(Node node){
            node.pre.next=node.next;
            node.next.pre=node.pre;
        }
    }

    class MinStack {

        private Stack<Integer> minStack;
        private Stack<Integer> stack;

        /** initialize your data structure here. */
        public MinStack() {
            minStack=new Stack<>();
            stack=new Stack<>();
        }

        public void push(int x) {
            if (minStack.empty()){
                minStack.push(x);
            }else {
                if (x<minStack.peek()){
                    minStack.push(x);
                }
            }
            stack.push(x);
        }

        public void pop() {
            Integer peek = stack.pop();
            if (peek==minStack.peek()){
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    public ListNode reverseList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode pre=null;
        ListNode cur=head;
        while (cur!=null){
            ListNode next = cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res={0,0};
        if (nums==null||nums.length==0){
            return res;
        }
        //K:num;V:index
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num1=nums[i];
            int num2=target-num1;
            if (map.containsKey(num2)){
                res[0]=i;
                res[1]=map.get(num2);
                return res;
            }
            map.put(num1,i);
        }
        return res;
    }

    private boolean[] used;
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        if (nums==null||nums.length==0){
            return res;
        }
        used=new boolean[nums.length];
        generate(nums,0,new ArrayList<>(),res);
        return res;
    }

    private void generate(int[] nums,int index,List<Integer> list,List<List<Integer>> res){
        if (index==nums.length){
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]){
                used[i]=true;
                list.add(nums[i]);
                generate(nums,index+1,list,res);
                used[i]=false;
                list.remove(list.size()-1);
            }
        }
    }

    public int maxSubArray(int[] nums){
        int res=Integer.MIN_VALUE;
        int curMax=0;
        for (int i:nums) {
            if (curMax>=0){
                curMax+=i;
            }else {
                curMax=i;
            }
            res=Math.max(res,curMax);

        }
        return res;
    }

    public List<Integer> topk(Integer[] nums,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
        k=nums.length-k+1;
        for (int i:nums){
            pq.offer(i);
            if (pq.size()>k){
                pq.poll();
            }
        }
        List<Integer> res=new ArrayList<>();
        for (int i:pq){
            res.add(i);
        }
        return res;
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1==null||s1.length()==0){
            return true;
        }
        int[] isContains1=new int[26];
        int[] isContains2=new int[26];
        for (char c:s1.toCharArray()){
            isContains1[c-'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            if (i>=s1.length()){
                isContains2[s2.charAt(i-s1.length())-'a']--;
            }
            isContains2[s2.charAt(i)-'a']++;
            if (Arrays.equals(isContains1,isContains2)){
                return true;
            }
        }
        return false;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root==null){
            return false;
        }
        if (root.left==null&&root.right==null){
            if (root.val==sum){
            return true;
            }else {
                return false;
            }
        }
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }

    public int minBean(int[] nums){
        int n = nums.length;
        if (n<6){
            return 0;
        }
        //memo[i]:表示松鼠跳到下标为(i-1)的位置的捡豆子最少数量
        int[] memo=new int[n+1];
        memo[0] = 0;
        memo[3] = nums[2];
        memo[4] = nums[3];
        memo[5] = nums[4];
        memo[6] = nums[2] + nums[5];
        memo[7] = nums[2] + nums[6];
        for (int i = 8; i <=n; i++) {
            memo[i] = (Math.min(Math.min(memo[i-3] ,memo[i - 4]),memo[i - 5])+nums[i-1]);
        }
        return memo[n];
    }










    @Test
    public void test(){


        String str=new StringBuilder("计算机").append("软体").toString();
        String str2="计算机软体";
        /*String str2="java";
        System.out.println(str2.intern()==str2);*/
        System.out.println(str.intern()==str2);

    }

    private void FileRead(String filePath){
        File file = new File(filePath);
        File[] files = file.listFiles();
        List<Integer> list=new ArrayList<>();
        int i=1;
        for (File f:files){
            if (f.getName().contains("Solution")){
                String[] solutions = f.getName().split("\\.");
                String substring = solutions[0].substring(8);
                int num = Integer.parseInt(substring);
                list.add(num);
                Collections.sort(list);
            }
        }
        System.out.println(list);
        System.out.println(list.size());
    }





}
