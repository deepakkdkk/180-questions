// 90. Reverse of a string
class Solution {
    public String reverseWords(String s) {
        
        String[] ans = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        
        int si = 0;
        int ei = ans.length - 1;
        while(si <= ei){
            String str = ans[si];
            ans[si] = ans[ei];
            ans[ei] = str;
            si++;
            ei--;
        }
        for(int i = 0; i < ans.length - 1; i++){
            sb.append(ans[i]);
            sb.append(" ");
        }
        sb.append(ans[ans.length - 1]);
        return sb.toString();
    }
}

// 91. Longest palindromic substring
// T.C = O(n ^ 2) S.C= O(n ^ 2)
class Solution {
    public String longestPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int count = 0;
        int min = 0;
        int max = 0;
        for(int g = 0; g < dp.length; g++){
            for(int i = 0, j = g; j < dp[0].length; i++,j++){
                if((j - i) == 0){
                    dp[i][j] = 1;
                    count++;
                }else if((j - i) == 1){
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = 1;
                        count++;
                        min = i;
                        max = j;
                    }else{
                        dp[i][j] = 0;
                    }
                }else if((j - i) > 1){
                    if(s.charAt(i) == s.charAt(j)){
                        if(dp[i + 1][j - 1] == 1){
                            dp[i][j] = 1;
                            count++;
                            min = i;
                            max = j;
                        }else{
                            
                        }
                    }
                }
            }
        }
        return s.substring(min, max + 1);
    }
}