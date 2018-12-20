package backTrack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution690 {
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};

    //K:id;V:Employee
    private Map<Integer,Employee> importanceMap=new HashMap<>();
    public int getImportance(List<Employee> employees, int id) {
        for (Employee e:employees){
            importanceMap.put(e.id,e);
        }
        return dfs(id);
    }

    //深度遍历
    private int dfs(int id){
        int res = importanceMap.get(id).importance;
        for (Integer sid:importanceMap.get(id).subordinates){
            res+=dfs(sid);
        }
        return res;
    }
}
