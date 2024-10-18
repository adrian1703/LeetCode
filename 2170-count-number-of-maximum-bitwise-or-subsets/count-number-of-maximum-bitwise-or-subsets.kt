class Solution {
    fun countMaxOrSubsets(nums: IntArray): Int {
        var maxBitCount = 0
        for (i in nums.indices) {
            maxBitCount = maxBitCount or nums[i]
        }
        val mustHave = getMustHaveCandidates( nums, maxBitCount)
        val powerSet = powerSet(
            nums.indices.toMutableList(),
            mustHave)
        filterPowerSet(powerSet, mustHave)
        checkBits(powerSet, maxBitCount, nums)

        return powerSet.size
    }

    private fun getMustHaveCandidates(nums: IntArray, maxBitCount: Int): List<Int> {
        val mustHave = mutableListOf<Int>()
        nums.forEachIndexed { index, num ->
            if (num.countLeadingZeroBits() == maxBitCount.countLeadingZeroBits()) {
                mustHave.add(index)
            }
        }
        return mustHave.toList()
    }

    private fun powerSet(
        current: MutableList<Int>,
        mustHaveCandidates: List<Int>
    ):MutableList<MutableList<Int>> {
        if(current.size == 0)
            return mutableListOf(mutableListOf())
        
        val element = current[0]
        val other = current.slice(1..<current.size).toMutableList()

        val setsWithoutElement: MutableList<MutableList<Int>> = powerSet(other, mustHaveCandidates)

        val subsetsWithElement = mutableListOf<MutableList<Int>>()
        for(subset in setsWithoutElement) {
            subsetsWithElement.add((listOf(element) + subset).toMutableList())
        }
        val rList = mutableListOf<MutableList<Int>>()
        rList.addAll(setsWithoutElement)
        rList.addAll(subsetsWithElement)

        return rList
    }
    private fun filterPowerSet(powerSet: MutableList<MutableList<Int>>, candidates:List<Int>) {
        powerSet.removeIf { setCandidate -> !setCandidate.any { e:Int -> candidates.contains(e) } }
    }

    private fun checkBits(powerSet: MutableList<MutableList<Int>>, maxBitCount: Int, nums: IntArray ) {
        powerSet.removeIf {
            var currentBitCount = 0
            var found = false
            for (i in it) {
                currentBitCount = currentBitCount or nums[i]
                if(currentBitCount == maxBitCount) {
                    found = true
                    break
                }
            }
            return@removeIf !found
        }
    }
}