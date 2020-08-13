package com.lzw.knowledge.knowledge.leetcode;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

 示例:

 X X X X
 X O O X
 X X O X
 X O X X
 运行你的函数后，矩阵变为：

 X X X X
 X X X X
 X X X X
 X O X X
 解释:

 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/surrounded-regions
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_4 {
    public void solve(char[][] board) {
        if(board == null || board.length == 0) return;
        int n = board.length;//二维数组的行数
        int m = board[0].length;//二维数组的列数
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                //从边界开始，如果是‘O’，就进行深度优先遍历，dfs方法递归遍历四周的节点
                boolean flag = i == 0 || j == 0 || i == n-1 || j == m-1;
                if(flag && board[i][j] == 'O'){
                    dfs(board, i, j);
                }

            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * dfs深度优先算法遍历，从
     * @param board
     * @param i
     * @param j
     */
    public void dfs(char[][] board, int i, int j){
        //将边界不需要改为X的O先改为#，节省遍历时间
        if(i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] == 'X' || board[i][j] == '#'){
            return;
        }
        board[i][j] = '#';
        dfs(board, i, j+1);
        dfs(board, i, j-1);
        dfs(board, i+1, j);
        dfs(board, i-1, j);
    }

    public static void main(String[] args) {
        char[][] nums = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        Solution_4 solution_4 = new Solution_4();
        solution_4.solve(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++){
                System.out.print("\'" + nums[i][j] + "\'" + ",");
            }
        }
    }
}
