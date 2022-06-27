// count Distinct substring
// T.C = O(n ^ 2) (Coding ninjas) we optimized space using trie
public class Solution 
{
    static class Node{
        Node[] links = new Node[26];
        boolean flag = false;
        Node(){
            
        }
        
        boolean containsKey(char ch){
            return links[ch - 'a'] != null;
        }
        Node get(char ch){
            return links[ch - 'a'];
        }
        void put(char ch, Node node){
            links[ch - 'a'] = node;
        }
        
        void setEnd(){
            flag = true;
        }
    }
	public static int countDistinctSubstrings(String s) 
	{
		//	  Write your code here.
        Node root = new Node();
        int n = s.length();
        int count = 0;
        for(int i = 0; i < n; i++){
            Node node = root;
            for(int j = i; j < s.length(); j++){
                if(!node.containsKey(s.charAt(j))){
                    node.put(s.charAt(j), new Node());
                    count++;
                }
                node = node.get(s.charAt(j));
            }
        }
        return count + 1;
	}
}

//Power set (GFG)
// T.C = O(2^n * n)
class Solution
{
    public List<String> AllPossibleStrings(String s)
    {
        // Code here
        List<String> ans = new ArrayList<>();
        int n = s.length();
        for(int i = 1; i < (int)(Math.pow(2, n)); i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++){
                if((i & (1 << j)) != 0){
                    sb.append(s.charAt(j));
                }
            }
            ans.add(sb.toString());
        }
        Collections.sort(ans);
        return ans;
    }
}


// Maximum XOR of two element in an array
class Solution {
    //T.C = (first array)O(n * 32) + (second array)O(m * 32)  but we cannot specify space complexity because collision occurst most here.
    class Node{
        Node links[] = new Node[2];
        
        Node(){
            
        }
        boolean containsKey(int ind){
            return (links[ind] != null);
        }
        
        Node get(int ind){
            return links[ind];
        }
        
        void put(int ind, Node node){
            links[ind] = node;
        }
    }
    
    class Trie{
        Node root;
        Trie(){
            root = new Node();
        }
        void insert(int num){
            Node node = root;
            for(int i = 31; i >= 0; i--){
                int bit = (num >> i) & 1;
                if(!node.containsKey(bit)){
                    node.put(bit, new Node());
                }
                node = node.get(bit);
            }
        }
        
        int getMax(int num){
            Node node = root;
            int maxNum = 0;
            for(int i = 31; i >= 0; i--){
                int bit = (num >> i) & 1;
                if(node.containsKey(1 - bit)){
                    maxNum = maxNum | (1 << i);
                    node = node.get(1 - bit);
                }else{
                    node = node.get(bit);
                }
            }
            return maxNum;
        }
    }
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        for(int i = 0; i < nums.length; i++){
            trie.insert(nums[i]);
        }
        int maxi = 0;
        for(int i = 0; i < nums.length; i++){
            maxi = Math.max(maxi, trie.getMax(nums[i]));
        }
        return maxi;
    }
}

// Maximum XOR With an Element From Array
class Solution {
    class Node{
        Node links[] = new Node[2];
        
        Node(){
            
        }
        boolean containsKey(int ind){
            return (links[ind] != null);
        }
        
        Node get(int ind){
            return links[ind];
        }
        
        void put(int ind, Node node){
            links[ind] = node;
        }
    }
    
    class Trie{
        Node root;
        Trie(){
            root = new Node();
        }
        void insert(int num){
            Node node = root;
            for(int i = 31; i >= 0; i--){
                int bit = (num >> i) & 1;
                if(!node.containsKey(bit)){
                    node.put(bit, new Node());
                }
                node = node.get(bit);
            }
        }
        
        int getMax(int num){
            Node node = root;
            int maxNum = 0;
            for(int i = 31; i >= 0; i--){
                int bit = (num >> i) & 1;
                if(node.containsKey(1 - bit)){
                    maxNum = maxNum | (1 << i);
                    node = node.get(1 - bit);
                }else{
                    node = node.get(bit);
                }
            }
            return maxNum;
        }
    }
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        
        ArrayList<ArrayList<Integer>> offlineQueries = new ArrayList<>();
        for(int i = 0; i < queries.length; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(queries[i][1]);
            temp.add(queries[i][0]);
            temp.add(i);
            offlineQueries.add(temp);
        }
         Collections.sort(offlineQueries, new Comparator<ArrayList<Integer>> () {
            @Override
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                return a.get(0).compareTo(b.get(0));
            }
        });
        Trie trie = new Trie();
        int idx = 0;
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            while(idx < nums.length && nums[idx] <= offlineQueries.get(i).get(0)){
                trie.insert(nums[idx]);
                idx++;
            }
            
            if(idx == 0){
                int qidx = offlineQueries.get(i).get(2);
                ans[qidx] = -1;
            }else{
                int qidx = offlineQueries.get(i).get(2);
                int max = trie.getMax(offlineQueries.get(i).get(1));
                ans[qidx] = max;
            }
        }
        return ans;
    }
}