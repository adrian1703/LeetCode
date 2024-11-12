class Solution {
    fun intToRoman(num: Int): String {
        val codes = HashMap<Int,Pair<Char,Char>>()
        codes[0] = Pair('I','V')
        codes[1] = Pair('X','L')
        codes[2] = Pair('C','D')
        codes[3] = Pair('M','?')
        val sb = StringBuilder()
        // 1 <= num <= 3999
        var numLocal : Int = num 
        var i = 0
        while(i < 4) {
            when(val toEncode = numLocal % 10) {
                0,1,2,3 -> (1..toEncode).forEach { _ -> sb.append(codes[i]!!.first) }
                4 -> sb.append(codes[i]!!.second).append(codes[i]!!.first)
                5,6,7,8 -> {
                    (6..toEncode).forEach { _ -> sb.append(codes[i]!!.first) }
                    sb.append(codes[i]!!.second)
                } 
                9 -> sb.append(codes[i+1]!!.first).append(codes[i]!!.first)
            }
            numLocal /= 10
            i++
        }
        return sb.reverse().toString()
    }
}