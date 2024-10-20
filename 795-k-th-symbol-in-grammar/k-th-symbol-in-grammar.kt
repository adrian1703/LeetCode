class Solution {
    fun kthGrammar(n: Int, k: Int): Int {
        return if(stepDown(n, k - 1)) 1 else 0
    }

    private fun stepDown(prevRow: Int, k: Int): Boolean {
        if (prevRow <= 1) return false

        val prevValue = stepDown(prevRow - 1, k / 2)
        val currentValue = !prevValue
        return if (k % 2 == 0) {
            !currentValue
        } else {
            currentValue
        }
    }
}