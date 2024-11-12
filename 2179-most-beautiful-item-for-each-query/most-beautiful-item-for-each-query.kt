class Solution {
   fun maximumBeauty(items: Array<IntArray>, queries: IntArray): IntArray {
        //queries = price
        //items = price, beauty
        items.sortBy {it[0]}
        var answer = Array(queries.size) { IntArray(2) }
        answer.indices.forEach { i ->
            answer[i][0] = queries[i]
            answer[i][1] = i
        }
        answer.sortBy { it[0] } // price, index
        var slideItemsValue  : Pair<Int, Int>  = slideItems(items, answer[0][0], 0,  0)
        var slideAnwserValue : Pair<Int, Int>? = slideAnswer(answer, slideItemsValue.second, 0)
        while(slideAnwserValue != null) {
            slideItemsValue  = slideItems(items, slideAnwserValue.second, slideItemsValue.second, slideItemsValue.first)
            slideAnwserValue = slideAnswer(answer, slideItemsValue.second, slideAnwserValue.first)
        }
        answer.sortBy { it[1] }
        return answer.map { it[0]}.toIntArray()
    }

    //returns index, beauty
    fun slideItems(items: Array<IntArray>, untilPrice: Int, oldMaxBeauty: Int, indexStart: Int):Pair<Int,Int> {
        var currentIndex = indexStart
        var currentPrice = -1
        var currentMaxBeauty = oldMaxBeauty
        while(currentIndex < items.size && currentPrice <= untilPrice) {
            currentPrice     = max(currentPrice,     items[currentIndex][0])
            if(currentPrice > untilPrice)
                break //break condition
            currentMaxBeauty = max(currentMaxBeauty, items[currentIndex][1])
            currentIndex++
        }

        return Pair(currentIndex, currentMaxBeauty)
    }

    //returns index, price
    //return null if done
    fun slideAnswer(answer: Array<IntArray>, beauty: Int, indexStart: Int):Pair<Int,Int>? {
        if(indexStart >= answer.size)
            return null // break condition+
        var currentIndex = indexStart
        val priceStart   = answer[indexStart][0]
        var currentPrice = answer[currentIndex][0]

        while(currentIndex < answer.size ) {
            currentPrice = answer[currentIndex][0]
            if(currentPrice > priceStart) {
                break
            }
            answer[currentIndex][0] = beauty
            currentIndex++
        }
        return Pair(currentIndex, currentPrice)
    }
}