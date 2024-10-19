class Solution {
    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        val letters = mutableListOf(
            Pair(a,'a') ,
            Pair(b,'b') ,
            Pair(c,'c')
        )
        var lastLetter:Char = '-'
        var lastlastLetter:Char = '-'
        val s = StringBuilder()
        var couldAppend = true
        while (couldAppend) {
            letters.sortByDescending { it.first }
            couldAppend = false
            for ( i in letters.indices) {
                val occurrencesLeft = letters[i].first
                val charToAppend = letters[i].second
                if ((charToAppend == lastLetter
                     && charToAppend == lastlastLetter)
                    || occurrencesLeft == 0
                ) {
                    continue
                }
                s.append(charToAppend)
                lastlastLetter = lastLetter
                lastLetter = charToAppend
                letters[i] = Pair(occurrencesLeft - 1, charToAppend)
                couldAppend = true
                break
            }
        }
        return s.toString()
    }
}