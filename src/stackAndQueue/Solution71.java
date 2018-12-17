package stackAndQueue;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 给定一个文档 (Unix-style) 的完全路径，请进行路径简化。
 *
 * 例如，
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 * 边界情况:
 *
 * 你是否考虑了 路径 = "/../" 的情况？
 * 在这种情况下，你需返回 "/" 。
 * 此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。
 * 在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。
 */
public class Solution71 {
    public String simplifyPath(String path) {
        if (path==null||path.length()==0){
            return null;
        }
        StringBuilder res=new StringBuilder("/");
        String[] paths = path.split("/");
        //为了方便遍历结果使用链表来模拟栈的结构
        LinkedList<String> stack=new LinkedList<>();
        for (int i = 0; i < paths.length; i++) {
            //..：表示上一级目录，此时需要弹栈，返回上一级目录
            if (paths[i].equals("..")){
                if (!stack.isEmpty()){
                    stack.removeLast();
                }
            }
            //跳过路径名为//（重复的/），和路径名为.
            else if (!paths[i].equals("")&&!paths[i].equals(".")){
                stack.add(paths[i]);
            }
        }
        for (int i = 0; i < stack.size(); i++) {
            if (i!=stack.size()-1){
                res.append(stack.get(i)+"/");
            }else {
                res.append(stack.get(i));
            }
        }
        return res.toString();

    }
    @Test
    public void test(){
        /*String s="..//..";
        String[] strs = s.split("/");
        for (String str:strs){
            if (str.equals("")){
                System.out.println("null");
            }else {
                System.out.print(str + ",");
            }
        }*/
        String p="/home//foo/";
        String res = simplifyPath(p);
        System.out.println(res);
    }
}