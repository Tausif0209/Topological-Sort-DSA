import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
         if(prerequisites.length<1) return true;
         int[] indegree=new int[numCourses];
        ArrayList<Integer>[] edges=new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++){
            edges[i]=new ArrayList<>();
        }
        for(int[] p:prerequisites){
            edges[p[1]].add(p[0]);
            indegree[p[0]]++;
        }
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0) queue.add(i);
        }
        int c=0;//courses covered count
        while(!queue.isEmpty()){
            int currNode=queue.poll();
            c++;
            for(Integer neighbour:edges[currNode]){
                indegree[neighbour]--;
                if(indegree[neighbour]==0) queue.add(neighbour);
            }
        }
        return c==numCourses;
    }}