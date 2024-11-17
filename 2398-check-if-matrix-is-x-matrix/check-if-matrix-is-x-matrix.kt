class Solution {
    fun checkXMatrix(grid: Array<IntArray>): Boolean {
        val n = grid.size - 1
        for (i in grid.indices) {
            for (j in grid.indices) {
                if ((i == j  || i + j == n) && grid[i][j] == 0) {
                    return false
                } 
                if (!(i == j  || i + j == n) && grid[i][j] != 0) {
                    return false
                }
            }
        }
        return true
    }
}