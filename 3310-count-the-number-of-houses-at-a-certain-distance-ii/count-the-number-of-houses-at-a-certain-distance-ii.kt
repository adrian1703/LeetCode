import kotlin.math.abs
import kotlin.math.min
class Solution {
    fun countOfPairs(n: Int, x: Int, y: Int): LongArray {
        val xLocal = if (x <= y) x else y
        val yLocal = if (x <= y) y else x
        val tracker = LongArray(n) {0L}

        if (x == y || yLocal - 1 == xLocal) {
            calcStraightLine(tracker, n)
            tracker.forEachIndexed { index, l -> print( "${index+1}: $l \t") }
            println()
            return tracker
        }
        calcLoop(tracker, xLocal, yLocal)
        calcWindow(tracker, xLocal, yLocal, n)
        calcStraightLine(tracker, xLocal)
        calcStraightLine(tracker, n - yLocal + 1)
        return tracker
    }

    private fun calcStraightLine(tracker: LongArray, n: Int): LongArray {
        for (index in 0 until n) {
            tracker[index] += (n - index - 1).toLong() * 2L
        }
        return tracker
    }

    // from n*(n-1) total this produces (y-x+1)(y-x)
    fun calcLoop(tracker: LongArray, x: Int, y: Int) {
        val loopSize = y - x
        val f = { index: Int -> ((loopSize - index).toLong() * 2L) }
        var i = 0
        while (i < (loopSize/2)) {
            tracker[i] += f(i) + f(loopSize - i - 1)
            i++
        }
        if(loopSize % 2L == 1L) {
            tracker[i] += f(i)
        }
    }

    fun calcWindow(tracker: LongArray, x: Int, y: Int, n:Int) {
        var xLeft  = 0
        var xRight = x - 1
        var yLeft  = x
        var yRight = (y + x) / 2
        var iter   = 0
        var iterMax = x + yRight + 1
        addOverlap(iter, iterMax, xLeft, xRight, yLeft, yRight, tracker)

        xLeft  = 0
        xRight = x - 1
        yLeft  = x
        yRight = y - (y + x) / 2 + x
        iter   = 0
        iterMax = x + yRight + 1
        addOverlap(iter, iterMax, xLeft, xRight, yLeft, yRight, tracker)

        xLeft  = (y + x) / 2 - 1
        xRight = y - 1
        yLeft  = y
        yRight = n
        iter   = 0
        iterMax = x + yRight + 1
        addOverlap(iter, iterMax, xLeft, xRight, yLeft, yRight, tracker)

        xLeft  = y - (y + x) / 2 + x - 1
        xRight = y - 1
        yLeft  = y
        yRight = n
        iter   = 0
        iterMax = x + yRight + 1
        addOverlap(iter, iterMax, xLeft, xRight, yLeft, yRight, tracker)

        xLeft  = 0
        xRight = x - 1
        yLeft  = y - (y - x ) + 1
        yRight = n - (y - x ) + 1
        iter   = 0
        iterMax = x + yRight + 1
        addOverlap(iter, iterMax, xLeft, xRight, yLeft, yRight, tracker)
    }

    private fun addOverlap(
        iter: Int,
        iterMax: Int,
        xLeft: Int,
        xRight: Int,
        yLeft: Int,
        yRight: Int,
        tracker: LongArray
    ){
        var iter1 = iter
        var xLeft1 = xLeft
        var xRight1 = xRight
        while (iter1 < iterMax) {
            val overlap = getOverlap(xLeft1, xRight1, yLeft, yRight).toLong() * 2
            if (overlap != 0L)
                tracker[iter1 - 1] += overlap
            xLeft1++
            xRight1++
            iter1++
        }
    }
    fun getOverlap(windowLeft: Int,windowRight: Int, windowTargetLeft: Int, windowTargetRight: Int): Int {
        return max(min(windowRight, windowTargetRight) - max(windowTargetLeft, windowLeft), 0)
    }
}