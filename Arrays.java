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

// 8. Merge Overlapping integervals
class Solution {
    
    public int[][] merge(int[][] arr) {
        Arrays.sort(arr,(a, b) -> (a[0] - b[0]));
        ArrayList<int[]> st = new ArrayList<>();
        st.add(arr[0]);
        for(int i = 1; i < arr.length; i++){
            if(arr[i][0] > st.get(st.size() - 1)[1]){
                st.add(arr[i]);
            }else{
                int[] temp = st.remove(st.size() - 1);
                temp[1] = Math.max(temp[1], arr[i][1]);
                st.add(temp);
            }
        }
        int[][] ans = new int[st.size()][2];
        return st.toArray(ans);
    }
}

// 9. Merge two sorted array without using extra space
//time complexity O(n * m)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        int k = 0;
        if(n == 0)
            return;
        for(int i = 0; i < m; i++){
            if(nums1[j] <= nums2[k]){
                j++;
                
            }else{
                swap(nums1, nums2, j, k);
                j++;
            }
            
        }
        for(int i = m; i < n + m; i++){
            nums1[i] = nums2[i - m];
        }
    }
    
    public void swap(int[] arr1, int arr2[], int i, int j){
        //swap
        arr1[i] = (arr1[i] + arr2[j]) - (arr2[j] = arr1[i]); 
        
        //fixing correct possition of first element of arr2
        for(int k = 1; k < arr2.length; k++){
            if(arr2[k - 1] < arr2[k]){
                break;
            }else{
                int temp = arr2[k - 1];
                arr2[k - 1] = arr2[k];
                arr2[k] = temp;
            }
        }
    }
}

//Time complexity O(n * log(n)) where n = sum of no. of elements of Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int gap = 0;
        if((m + n) % 2 == 1){
            gap = (m + n)/2 + 1;
        }else{
            gap = (m + n)/ 2;
        }
        for(int i = 0; i < n; i++){
            nums1[m + i] = nums2[i];
        }
        while(gap > 0){
            
            for(int j = 0, k = gap; k < n + m; k++, j++){
                if(nums1[j] <= nums1[k]){
                    
                }else{
                    nums1[j] = (nums1[j] + nums1[k]) - (nums1[k] = nums1[j]);
                }
            }
            if(gap == 1) break;
            if(gap % 2 == 1){
                gap = gap / 2 + 1;
            }else{
                gap = gap / 2 ;
            }
        }
    }
}

// 10. Find the duplicate Number
class Solution {
    public int findDuplicate(int[] nums) {
        int dup = 0;
        for(int i = 0; i < nums.length; i++){
            
            int idx = nums[i];
            if(nums[i] < 0){
                idx = -nums[i];
            }
            if(nums[idx] < 0){
                dup = idx;
                break;
            }else{
                nums[idx] *= -1;
            }
        }
        return dup;
    }
}

// 11. Repeat and missing number (Set mismatch)
public class Solution {
    public int[] findErrorNums(int[] nums) {
        int dup = 0;
        for(int i = 0; i < nums.length; i++){
            
            int idx = nums[i];
            if(nums[i] < 0){
                idx = -nums[i];
            }
            if(nums[idx - 1] < 0){
                dup = idx;
                
            }else{
                nums[idx - 1] *= -1;
            }
        }
        int miss = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                miss = i + 1;
            }
        }
        return new int[]{dup, miss};
    }
}

// 12. Inversion of Array (Count inversion)
class Solution
{
    // arr[]: Input Array
    // N : Size of the Array arr[]
    //Function to count inversions in the array.
    public static long mergeSort(long[] nums, int si, int ei) {
        if (si >= ei) {
            return 0;
        }
        int mid = (si + ei) / 2;
        long ans = 0;
        ans += mergeSort(nums, si, mid);
        ans += mergeSort(nums, mid + 1, ei);
        ans += mergeArrays(nums, si, ei, mid);
        return ans;
    }

    public static long mergeArrays(long[] nums, int si, int ei, int mid) {
        int s1 = si;
        int e1 = mid;
        int s2 = mid + 1;
        int e2 = ei;
        long ans = 0;
        long[] left = new long[e1 - s1 + 1];
        for (int i = 0; i < left.length; i++) {
            left[i] = nums[s1 + i];
        }
        long[] right = new long[e2 - s2 + 1];
        for (int i = 0; i < right.length; i++) {
            right[i] = nums[s2 + i];
        }
        int p1 = 0;// left
        int p2 = 0;// right
        int p = s1;// own
        while (p1 != left.length && p2 != right.length) {
            if (left[p1] <= right[p2]) {
                nums[p] = left[p1];
                p1++;
            } else {
                nums[p] = right[p2];
                p2++;
                ans += (left.length - p1);
            }
            p++;
        }
        while (p1 < left.length) {
            nums[p] = left[p1];
            p1++;
            p++;
        }
        while (p2 < right.length) {
            nums[p] = right[p2];
            p2++;
            p++;
        }
        return ans;
    }

    static long inversionCount(long arr[], long N)
    {
        // Your Code Here
        long ans = mergeSort(arr, 0, arr.length - 1);
        return ans;
    }
}

