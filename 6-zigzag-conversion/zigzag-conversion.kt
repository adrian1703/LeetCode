class Solution {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) {
            return s
        }

        var sb:StringBuilder = StringBuilder()
        val offset = 2 * (numRows - 1 )
        var iter = 0
        while (iter < numRows) {
            var iterInner = 0 + iter
            while (iterInner < s.length) {

                sb.append(s[iterInner])
                val candidate = iterInner + offset - iter * 2
                if (iter > 0 && iter < numRows - 1 && candidate < s.length && candidate > 0){
                    sb.append(s[candidate])
                }
                iterInner += offset
            }
            iter ++
        }
        return sb.toString()
    }
}