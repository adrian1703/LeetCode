class Solution {
    fun maximumSwap(num: Int): Int {
        val numArray = num.toString().toCharArray().map { it.digitToInt() }.toMutableList()
        val smallestIndexArray = IntArray(10) {-1}
        val highestIndexArray = IntArray(10) {-1}
        var left = 0
        var right = 0
        var matchFound = false
        numArray.forEachIndexed { index, value ->
            if (highestIndexArray[value] == -1) {
                highestIndexArray[value] = index
            }
            smallestIndexArray[value] = index
        }
        highestIndexArray.sort()
        for(indexLeft in highestIndexArray){
            if(indexLeft == -1)
                continue
            if (matchFound)
                break
            val valueLeft = numArray[indexLeft]
            for(valueRight in 9 downTo valueLeft + 1 ) {
                if (matchFound)
                    break
                val indexRight = smallestIndexArray[valueRight]
                if(indexRight == -1)
                    continue
                if(indexLeft < indexRight){
                    left = indexLeft
                    right = indexRight
                    matchFound = true
                }
            }
        }
        if (matchFound){
            swap(numArray, left, right)
            return arrayToInt(numArray)
        } else
            return num
    }

    fun swap(numArray: MutableList<Int>, i: Int, j: Int) {
        val temp = numArray[i]
        numArray[i] = numArray[j]
        numArray[j] = temp
    }

    fun arrayToInt(numArray: MutableList<Int>): Int {
        return numArray.joinToString("").toInt()
    }
}