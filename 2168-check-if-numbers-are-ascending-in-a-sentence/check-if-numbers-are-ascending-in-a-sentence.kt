class Solution {
    fun areNumbersAscending(s: String): Boolean {
        var currentNumber = -1
        var nextNumber = 0
        var counting = false
        for (element in s) {
            if(element.isDigit()) {
                nextNumber = nextNumber * 10 + element.digitToInt()
                counting = true
            } else if(counting) {
                if (nextNumber <= currentNumber)
                    return false
                currentNumber = nextNumber
                nextNumber = 0
                counting = false
            }
        }
        return if(counting) nextNumber > currentNumber else true
    }
}