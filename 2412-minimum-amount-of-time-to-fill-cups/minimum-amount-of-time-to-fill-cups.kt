class Solution {
    fun fillCups(amount: IntArray): Int {
        amount.sort()
        val a = amount[0]
        val b = amount[1]
        val c = amount[2]
        val total = a + b + c
        val overflow = total % 2 
        val min = (total / 2 ) + overflow
        return max(min, total - a - b)
    }
}