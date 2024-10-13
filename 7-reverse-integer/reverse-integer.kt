class Solution {
    fun reverse(x: Int): Int {
                var xString = x.toString()
        xString = xString.reversed()
        xString = if (xString.last() == '-') "-${xString.substring(0,xString.length -1 )}" else xString
        return try {
            Integer.parseInt(xString)
        } catch (_: Exception) {
            0
        }
    }
}