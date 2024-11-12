class Solution {
        fun lengthOfLongestSubstring(s: String): Int {
        var lookup :MutableMap<Char, Int> = mutableMapOf()
        var index = 0
        var start = 0
        var max   = 0
        while( index < s.length){
            val c = s[index]
            if(lookup.containsKey(c)){
                max   = max(max, index - start)
                start = lookup[c]!! + 1
                index = lookup[c]!!
                lookup = mutableMapOf<Char, Int>() //reset map
            } else {
                lookup[c] = index
            }

            index++
        }
        max = max(max, s.length - start)
        return max
    }
}