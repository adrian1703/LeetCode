class Solution {
   fun maxSumRangeQuery(nums: IntArray, requests: Array<IntArray>): Int {
        nums.sortDescending()
        val occMap = HashMap<Int, Int>()
        var sum = 0
        val mod = 10.0.pow(9.0).toInt() + 7
        val indexCount  = IntArray(nums.size)
        val arrayStarts = IntArray(requests.size)
        val arrayEnds   = IntArray(requests.size)
        requests.forEachIndexed { index, ints ->
            arrayStarts[index] = ints[0]
            arrayEnds[index] = ints[1]
        }
        arrayStarts.sort()
        arrayEnds.sort()
        var stackCount         = 0
        var arrayStartsPointer = 0
        var arrayEndsPointer   = 0
        indexCount.forEachIndexed { index, cnt ->
            while(index == arrayStarts.getOrNull(arrayStartsPointer)){
                stackCount ++
                arrayStartsPointer++
            }
            while(index == arrayEnds.getOrNull(arrayEndsPointer)?.plus(1)){
                stackCount--
                arrayEndsPointer++
            }
            indexCount[index] = stackCount
        }
        indexCount.sortDescending()
        indexCount.forEachIndexed { index, cnt ->
            sum += ((cnt.toLong() * nums[index] % mod)).toInt()
            sum %=  mod
        }
        return sum
    }
}