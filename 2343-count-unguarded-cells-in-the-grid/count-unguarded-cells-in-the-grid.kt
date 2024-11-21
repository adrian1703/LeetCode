class Solution {
    fun countUnguarded(m: Int, n: Int, guards: Array<IntArray>, walls: Array<IntArray>): Int {
        val matrix = Array(m) { IntArray(n) }
        for (i in guards){
            matrix[i[0]][i[1]] = -1
        }
        for (i in walls){
            matrix[i[0]][i[1]] = -1
        }
        val exploreNorth: (Pair<Int,Int>) -> Pair<Int,Int> = { loc -> Pair(loc.first + 1, loc.second) }
        val exploreSouth: (Pair<Int,Int>) -> Pair<Int,Int> = { loc -> Pair(loc.first - 1, loc.second) }
        val exploreEast:  (Pair<Int,Int>) -> Pair<Int,Int> = { loc -> Pair(loc.first , loc.second + 1) }
        val exploreWest:  (Pair<Int,Int>) -> Pair<Int,Int> = { loc -> Pair(loc.first , loc.second - 1) }
        for (i in guards) {
            val locGuard = Pair(i[0], i[1])
            explore(matrix, locGuard, exploreNorth)
            explore(matrix, locGuard, exploreSouth)
            explore(matrix, locGuard, exploreWest)
            explore(matrix, locGuard, exploreEast)
        }
        return matrix.sumOf { row -> row.map { cell -> if(cell == 0) 1 else 0 }.sum() }
    }

    fun explore(matrix: Array<IntArray>, startCell:Pair<Int,Int>, nextCell:(Pair<Int,Int>) -> Pair<Int,Int>){
        val next = nextCell(startCell)
        val m = next.first
        val n = next.second
        if(m >= matrix.size || m < 0 || n >= matrix[0].size || n < 0)
            return
        val cell = matrix[m][n]
        if (cell == -1) return
        matrix[m][n] = 1
        return explore(matrix, next,nextCell)
    }
}