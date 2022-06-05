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
<<<<<<< HEAD

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

