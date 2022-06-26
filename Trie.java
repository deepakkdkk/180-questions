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