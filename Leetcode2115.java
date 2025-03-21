class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> finishedRecipe=new ArrayList<>();
        HashMap<String,ArrayList<String>> adj=new HashMap<>();
        HashMap<String,Integer> ingredientsNeeded=new HashMap<>();
        int n=recipes.length;
        for(int i=0;i<n;i++){
             ingredientsNeeded.put(recipes[i],ingredients.get(i).size());
             for(String in:ingredients.get(i)){
                ArrayList<String> neighbor=adj.getOrDefault(in,new ArrayList<>());
                neighbor.add(recipes[i]);
                adj.put(in,neighbor);
             }
            } 
        Queue<String> queue=new LinkedList<>();
        for(String s:supplies) queue.offer(s);
        n=supplies.length;
        int i=0;
        while(!queue.isEmpty()){
            String ingredient=queue.poll();
            i++;
            if(i>n) finishedRecipe.add(ingredient);
            ArrayList<String> neighbors=adj.getOrDefault(ingredient,new ArrayList<>());
            for(String neighbor:neighbors){
                Integer ingredientsLeft=ingredientsNeeded.get(neighbor);
                ingredientsLeft--;
                if(ingredientsLeft==0) queue.offer(neighbor);
                ingredientsNeeded.put(neighbor,ingredientsLeft);
            }
        }
        return finishedRecipe;
    }
}