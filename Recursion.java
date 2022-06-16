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
        // o(n) for deep copy every subset
        ans.add(new ArrayList<>(temp));
        //o(2 * n) for generating all subsets
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

// 51. Combination sum -1
// Approach 1 using subsequence method 
class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        // Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, ans, temp, 0, target);
        return ans;

    }
    
    public void helper(int[] nums, int idx, List<List<Integer>> ans, List<Integer> temp, int sum, int target){
        if(idx == nums.length){
            
            return;
        }
        if(sum == target){
            ans.add(new ArrayList<>(temp));
            return;
        }
        if(sum > target) return;
        
        sum += nums[idx];
        temp.add(nums[idx]);
        helper(nums, idx, ans, temp, sum, target);
        temp.remove(temp.size() - 1);
        sum -= nums[idx];
        
        helper(nums, idx + 1, ans, temp, sum , target);
        
    }
}

// Approach 2
// T.C = O(2 ^ n)
class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        // Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, ans, temp, 0, target);
        return ans;

    }
    
    public void helper(int[] nums, int idx, List<List<Integer>> ans, List<Integer> temp, int sum, int target){
        if(sum > target){
            return;
        }
        if(sum == target){
            ans.add(new ArrayList<>(temp));
            return;
        }
        
        for(int i = idx; i < nums.length; i++){
            
            if(i == idx || nums[i] != nums[i - 1]){
                temp.add(nums[i]);           
                helper(nums, i, ans, temp, sum + nums[i], target);
                temp.remove(temp.size() - 1);
            }
        }
        
    }
}

// 52. Combination sum - 2
// T.C = O(2 * n)
class Solution {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, ans, temp, 0, target);
        return ans;
    }
    
     public void helper(int[] nums, int idx, List<List<Integer>> ans, List<Integer> temp, int sum, int target){
        if(sum > target){
            return;
        }
        if(sum == target){
            ans.add(new ArrayList<>(temp));
            return;
        }
        
        for(int i = idx; i < nums.length; i++){
            
            if(i == idx || nums[i] != nums[i - 1]){
                temp.add(nums[i]);           
                helper(nums, i + 1, ans, temp, sum + nums[i], target);
                temp.remove(temp.size() - 1);
            }
        }
        
    }
}

// 53. Palindrome partitioning
// T.C = O(n * 2 ^ n)
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        helper(ans, temp, 0, s);
        return ans;
        
    }
     public void helper(List<List<String>> ans, List<String> temp, int idx, String s){
        
        if(idx == s.length()){
            ans.add(new ArrayList<>(temp));
            return;
        }
        
        for(int i = idx; i < s.length(); i++){
            if(isPalindrome(s, idx, i)){
                temp.add(s.substring(idx, i + 1));
                helper(ans, temp, i + 1, s);
                temp.remove(temp.size() - 1);
            }
        }
    }
    boolean isPalindrome(String s, int start, int end) {
        while(start <= end) {
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}

// 54. Kth permutation sequence
// T.C = O(n * n), S.C = O(n)
class Solution {
    public String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> nums = new ArrayList<>();
        for(int i = 1; i < n; i++){
            fact = fact * i;
            nums.add(i);
        }
        nums.add(n);
        
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        while(true){
            sb.append(nums.get(k/fact));
            nums.remove(nums.get(k/fact));
            if(nums.size() == 0){
                break;
            }
            k = k % fact;
            fact = fact/(nums.size());
        }
        return sb.toString();
    }
}
