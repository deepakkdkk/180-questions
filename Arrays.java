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

// 4. Maximum subarray (Kadanes Algorithm)
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = nums[0];
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sum > max){
                max = sum;
            }
            if(sum < 0){
                sum = 0;
            }
        }
        return max;
    }
}

// 5. Sort Colors (Sort 0,1,2)
class Solution {
    public void sortColors(int[] nums) {
        int i = 0;
        int mid = 0;
        int k = nums.length - 1;
        while(mid <= k){
            if(nums[mid] == 0){
                swap(nums, i, mid);
                mid++;
                i++;    // before i all are Zeroes
            }else if(nums[mid] == 2){
                swap(nums, mid, k);
                k--;    // after k all are 2's
            }else{
                mid++;
            }
        }
    }
    
    public void swap(int[] nums, int i, int j){
        nums[i] = (nums[i] + nums[j]) - (nums[j] = nums[i]);
    }
}

// 6. Best time to buy and sell stock
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] < min){
                min = prices[i];
            }
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
}

// approach 2 using 3D dp
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[len + 1][2][2];
        for (int i = 0; i <= len; i++) {
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        dp[0][1][1] = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i - 1]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i - 1]);
        }
        return dp[len][1][0];
    }
}

// approach 3 using array
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[] sell = new int[len + 1];
        int[] buy = new int[len + 1];

        buy[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
            buy[i] = Math.max(buy[i - 1], -prices[i - 1]);
        }
        return sell[len];    
    }
}

//approach 4 using constant extra space and meaningful variables
class Solution {
    public int maxProfit(int[] prices) {
         int len = prices.length;
        int sell = 0;
        int buy = Integer.MIN_VALUE;
        for (int ele : prices) {
            sell = Math.max(sell, buy + ele);
            buy = Math.max(buy, -ele);
        }
        return sell;    
    }
}

// 7. Rotate image 
class Solution {
    public void rotate(int[][] arr) {
        // Transpose
        for(int i = 0; i < arr.length; i++){
         for(int j = i; j < arr.length; j++){
            int temp = arr[i][j];
            arr[i][j]= arr[j][i];
            arr[j][i] = temp;
         }
      }
      // reverse columns, row by row
      for(int i = 0; i < arr.length; i++){
         int li = 0;
         int ri = arr[0].length - 1;
         while(li <= ri){
            int temp = arr[i][li];
            arr[i][li] = arr[i][ri];
            arr[i][ri] = temp;

            li++;
            ri--;
         }
      }
    }
}
