class Solution {
    fun search(nums: IntArray, target: Int): Int {
        val rotatedElement = findPivot(nums,0, nums.lastIndex)
        if(rotatedElement == -1) {
            return bSearch(nums, 0, nums.lastIndex, target)
        } else {
            val result1 = bSearch(nums, 0, rotatedElement, target)
            val result2 = bSearch(nums, rotatedElement, nums.lastIndex, target)
            return max(result1,result2)
        }
    }

    fun bSearch(nums: IntArray, left:Int, right:Int, target: Int):Int{
        val pIndex = (right + left) / 2 //pivot rundet ab
        val pValue = nums[pIndex]
        if(pValue == target)
            return pIndex
        if(right == left)
            return -1
        if(pValue > target)
            return bSearch(nums, left, pIndex, target)
        else
            return bSearch(nums, pIndex + 1, right, target)
    }

    fun findPivot(nums: IntArray, left:Int, right:Int,): Int {
        if(right == left)
            return -1
        val pIndex = (right + left) / 2 //pivot rundet ab
        val pValue = nums[pIndex]
        val p1Value = nums[pIndex + 1]
        if(pValue >  p1Value)
            return pIndex + 1
        if(pValue < nums[left])
            return findPivot(nums, left, pIndex)
        else
            return findPivot(nums, pIndex + 1, right)
    }
}