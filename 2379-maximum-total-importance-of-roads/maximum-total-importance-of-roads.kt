class Solution {
    fun maximumImportance(n: Int, roads: Array<IntArray>): Long {
        val connections = LongArray(n)  // i = city -> value = outgoing connections
        for(road in roads) {
            connections[road[0]] += 1L
            connections[road[1]] += 1L
        }
        connections.sort()
        return connections.mapIndexed { index, i -> (index + 1) * i }.reduce{ a, b -> a + b }
    }
}