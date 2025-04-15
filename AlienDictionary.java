import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    public String findOrder(String[] word) {
    StringBuilder sb=new StringBuilder();
        HashMap<Character, ArrayList<Character>> adj=new HashMap<>();
        HashMap<Character,Integer> indegree=new HashMap<>();
        Queue<Character> queue=new LinkedList<>();
        for (String w : word) {
            for (char ch : w.toCharArray()) {
                indegree.putIfAbsent(ch, 0);
            }
        }
        for(int i=1;i<word.length;i++){
              if (word[i-1].length() > word[i].length() && word[i-1].startsWith(word[i])) return "";
            int len=Math.min(word[i].length(),word[i-1].length());
            for(int j=0;j<len;j++){
                char ch1=word[i-1].charAt(j);
                char ch2=word[i].charAt(j);
                if(ch1==ch2) continue;
                ArrayList<Character> neighbors=adj.getOrDefault(ch1,new ArrayList<>());
                neighbors.add(ch2);
                adj.put(ch1,neighbors);
                if(!indegree.containsKey(ch1)) indegree.put(ch1,0);
                indegree.put(ch2,indegree.getOrDefault(ch2,0)+1);
                break;
            }
        }
        for(Character key:indegree.keySet())
        {
            if(indegree.get(key)==0) queue.offer(key);
        }
        while(!queue.isEmpty()){
            char ch=queue.poll();
            sb.append(ch);
            ArrayList<Character> neighbors=adj.getOrDefault(ch,new ArrayList<>());
            for(Character neighbor:neighbors){
                indegree.put(neighbor,indegree.get(neighbor)-1);
                if(indegree.get(neighbor)==0) queue.offer(neighbor);
            }
        }
        return (sb.length()==indegree.size())? sb.toString():"";
    }
}
