class Solution {
    fun largestOddNumber(num: String): String {
        var iter = num.length - 1
        while (iter >= 0) {
            if (num[iter] in charArrayOf('1', '3', '5', '7', '9')) {
                return num.substring(0, iter + 1)
            }
            iter--
        }
        return ""
    }
}