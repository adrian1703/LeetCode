class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
                val lookup = mutableMapOf<Int, Int>()
        var i = 0
        var currentNum: Int
        var targetNum : Int
        while(true) {
            currentNum = nums[i]
            targetNum  = target - currentNum
            if(lookup.containsKey(targetNum)){
                return intArrayOf(lookup[targetNum]!!, i)
            }
            lookup[currentNum] = i
            i++
        }
    }
}