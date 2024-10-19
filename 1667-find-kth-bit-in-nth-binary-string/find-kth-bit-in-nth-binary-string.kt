class Solution {
    fun findKthBit(n: Int, k: Int): Char {
        return findKthBitInDepthI(2, k, StringBuilder("0") )
    }

    fun findKthBitInDepthI(i: Int, k: Int, s:StringBuilder):Char {
        val currentMax = s.length * 2 - 1
        enrichS(s)
        if (k < currentMax)
            return s.elementAt(k - 1)
        return findKthBitInDepthI(i + 1, k, s)
    }

    fun enrichS(s:StringBuilder) {
        s.append('1')
        for (i in s.length - 2 downTo 0 ) {
            s.append(if(s[i] == '0') '1' else '0')
        }
    }
}