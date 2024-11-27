class Solution {
    fun shortestDistanceAfterQueries(n: Int, queries: Array<IntArray>): IntArray {
        val distances = initDistMatrix(n)
        val shortestDistances = IntArray(queries.size)
        for (i in queries.indices) {
            update(distances, queries[i])
            shortestDistances[i] = getDistance(distances, 0, n -1)
        }
        return shortestDistances
    }

    fun update(distances: Array<IntArray>, query: IntArray) {
        val cityA           = query[0] // Edges
        val cityB           = query[1]
        val currentDistAB   = getDistance(distances, cityA, cityB)// Edges
        val newDistanceAB     = 1
        if(newDistanceAB >= currentDistAB) // no update needed
            return
        setDistance(distances, cityA, cityB, newDistanceAB)
        do {
            var converged = true
            val firstCity = 0
            val lastCity = distances[0].size
            for (i in firstCity..<lastCity) {
                for(j in i..<lastCity) {
                    val cities = intArrayOf(cityA, cityB, i, j).sorted()
                    val oldDistance = getDistance(distances, i, j)
                    val newPossibleDistance = getDistance(distances, cities[0], cities[1]) + getDistance(distances, cities[2], cities[3]) + newDistanceAB
                    if (newPossibleDistance < oldDistance && isBidirectionalSatisfied(i,j,cityA,cityB)) {
                        converged = false
                        setDistance(distances, i, j, newPossibleDistance)
                    }
                }
            }
            if(!getIsBidirect())
                break
        } while (!converged)
    }

    fun getIsBidirect():Boolean {
        return false
    }

    fun isBidirectionalSatisfied(i: Int, j: Int, cityA: Int, cityB: Int): Boolean {
        val bidirectional = getIsBidirect()
        if(bidirectional)
            return true
        //unidirectional here
        if(j < cityB || i > cityA) // cant go backwards
            return false
        else return true // can go forward
    }

    /**
     * 0    0
     * 1    1   0
     * 2    2   1   0
     * 3    3   2   1   0
     *
     *      0   1   2   3
     */
    fun initDistMatrix(n: Int): Array<IntArray> {
        return Array(n) { i -> IntArray(n - i) { j -> n - j - 1 - i } }
    }

    fun getDistance(distances: Array<IntArray>, cityA: Int, cityB: Int): Int {
        return distances[cityA][distances[0].size - cityB - 1]
    }

    fun setDistance(distances: Array<IntArray>, cityA: Int, cityB: Int, value:Int) {
        distances[cityA][distances[0].size - cityB - 1] = value
    }

}
