// 1. set matrix zeroes
class Solution {
    
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean row = false;
        boolean col = false;
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 0) col = true;
        }
        for(int i = 0; i < m; i++){
            if(matrix[0][i] == 0) row = true;
        }
        
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        for(int i = 1; i < m; i++){
            if(matrix[0][i] == 0){
                for(int j = 1; j < n; j++){
                    matrix[j][i] = 0;
                }
                
            }
        }
        for(int i = 1; i < n; i++){
            if(matrix[i][0] == 0){
                for(int j = 1; j < m; j++){
                    matrix[i][j] = 0;
                }
                
            }
        }
        if(row == true){
            for(int i = 0; i < m; i++){
                matrix[0][i] = 0;
            }
        }
        if(col == true)
            for(int i = 0; i < n; i++){
                matrix[i][0] = 0;
            }
    }
}


// 2. Pascal's Triangle
class Solution {
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sans = new ArrayList<>();
        sans.add(1);
        ans.add(new ArrayList<>(sans));
        if(numRows == 1){
            return ans;
        }
        sans.add(1);
        
        ans.add(new ArrayList<>(sans));
        if(numRows == 2){
            return ans;
        }
        
        for(int i = 3; i <= numRows; i++){
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j = 1; j <= i - 2; j++){
                int val1 = ans.get(i - 2).get(j - 1);
                int val2 = ans.get(i - 2).get(j);
                int res = val1 + val2;
                temp.add(res);
            }
            temp.add(1);
            ans.add(temp);
        }
        return ans;
    }
}

// 3. Next Permutation 
// all permutation approach (TLE)
class Solution {
    public void nextPermutation(int[] nums) {
        ArrayList<Integer> snums = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            snums.add(nums[i]);
        }
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];
        Arrays.sort(nums);
        helper(nums, ans, temp, freq);
        System.out.println(ans);
        for(int i = 0; i < ans.size(); i++){
            ArrayList<Integer> sans = ans.get(i);
            if(sans.equals(snums) == true && i != ans.size() - 1){
                ArrayList<Integer> fans = ans.get(i + 1);
                for(int j = 0; j < nums.length; j++){
                    nums[j] = fans.get(j);
                    
                }
                return ;
            }
            if(i == ans.size() - 1){
                ArrayList<Integer> fans = ans.get(0);
                for(int j = 0; j < nums.length; j++){
                    nums[j] = fans.get(j);
                    
                }
                return;
            }
        }
        
    }
    
    public void helper(int[] nums, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp,boolean[] freq){
        if(temp.size() == nums.length){
         if(ans.contains(temp) == false)
        ans.add(new ArrayList<>(temp));   
            return;
        }
        
        for(int i = 0; i < nums.length; i++){
            if(freq[i] == false){
                freq[i] = true;
                temp.add(nums[i]);
            helper(nums, ans, temp, freq);
            temp.remove(temp.size() - 1);
                freq[i] = false;
            }
            
        }
    }
}

// Optimal approach
class Solution {
    public void nextPermutation(int[] nums) {
        for(int i = nums.length - 1; i >= 1; i--){
            if(nums[i] > nums[i - 1]){
                int t = i - 1;
                int m = -1;
                for(int j = nums.length - 1; j >= 1; j--){
                    if(nums[j] > nums[t]){
                        swap(nums, j, t);
                        reverse(nums, t + 1, nums.length - 1);
                        return;
                    }
                }
            }
        }
        reverse(nums, 0, nums.length - 1);
    }
    
    public void swap(int[] nums, int i, int j){
        nums[i] = (nums[i] + nums[j]) - (nums[j] = nums[i]);
    }
    
    public void reverse(int[] nums, int i, int j){
        while(i <= j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}

