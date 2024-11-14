class Solution {
    fun numFriendRequests(ages: IntArray): Int {
        ages.sort()
        var numFriendRequests = 0
        var i = 0
        var min = 0
        var lastNum = -1
        var lastFriends = 0
        while (i < ages.size) {
            if (ages[i] == lastNum){
                numFriendRequests += lastFriends
                i++
                println(numFriendRequests)
                continue
            }

            val left = findFriendThresholdLeft(ages, min, ages.size - 1, ages[i] * 0.5 + 7)
            val right = findFriendThresholdRight(ages, min, ages.size - 1, (ages[i] + 1).toDouble())

            lastFriends = max(right - left, 0)
            numFriendRequests += lastFriends
            min = left
            lastNum = ages[i]
            i++
        }
        return numFriendRequests
    }

    fun findFriendThresholdLeft(ages: IntArray, left: Int, right: Int, req: Double): Int {
        if(right <= left)
            return left
        val pIndex  = (left + right) / 2
        val pValue  = ages[pIndex]
        val p1Value = ages[pIndex + 1]
        if(pValue <= req && p1Value > req) {
            return pIndex + 1
        }
        if(pValue > req && p1Value > req)
            return findFriendThresholdLeft(ages, left, pIndex, req)
        else
            return findFriendThresholdLeft(ages, pIndex + 1, right, req)
    }

    fun findFriendThresholdRight(ages: IntArray, left: Int, right: Int, req: Double): Int {
        if(right <= left)
            return left
        val pIndex  = (left + right) / 2
        val pValue  = ages[pIndex]
        val p1Value = ages[pIndex + 1]
        if(pValue < req && p1Value >= req) {
            return pIndex
        }
        if(pValue < req && p1Value < req)
            return findFriendThresholdRight(ages, pIndex + 1, right, req)
        else
            return findFriendThresholdRight(ages, left, pIndex, req)
    }

}