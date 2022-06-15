// 49. Subset sums
// T.C = O(2^n + 2^nlog(2^n) for sorting)
class Solution{
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        helper(arr, 0, ans, 0);
        Collections.sort(ans);
        return ans;

    }
    
    public void helper(ArrayList<Integer> arr, int idx, ArrayList<Integer> ans, int sum){
        if(idx == arr.size()){
            ans.add(sum);
            return;
        }
        sum += arr.get(idx);
        
        helper(arr, idx + 1, ans, sum);
        sum -= arr.get(idx);
        
        helper(arr, idx + 1, ans, sum);
    }
}

// 50. Subsets II
// T.C = O(2^n * n), S.C= o(2 * n) * o(k) because assuming every subset has length k
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, ans, temp);
        return ans;
    }
    
    public void helper(int[] nums, int idx, List<List<Integer>> ans, List<Integer> temp){
        
        ans.add(new ArrayList<>(temp));
        for(int i = idx; i < nums.length; i++){
            
            if(i == idx || nums[i] != nums[i - 1]){
                temp.add(nums[i]);           
                helper(nums, i + 1, ans, temp);
                temp.remove(temp.size() - 1);
            }
        }
        
    }
}
// with iteration
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<Integer>());

        int subsetSize = 0;

        for (int i = 0; i < nums.length; i++) {
            int startingIndex = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
            // subsetSize refers to the size of the subset in the previous step. This value also indicates the starting index of the subsets generated in this step.
            subsetSize = subsets.size();
            for (int j = startingIndex; j < subsetSize; j++) {
                List<Integer> currentSubset = new ArrayList<>(subsets.get(j));
                currentSubset.add(nums[i]);
                subsets.add(currentSubset);
            }
        }
        return subsets;
    }
}