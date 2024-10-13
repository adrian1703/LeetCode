class Solution {
     fun areNumbersAscending(s: String): Boolean {
        var currentNumber = -1
        var nextNumber = 0
        var counting = false
        var index = 0
        while (index < s.length) {
            if(s[index].isDigit()) {
                nextNumber = nextNumber * 10 + s[index].digitToInt()
                counting = true
            } else if(counting) {
                if (nextNumber <= currentNumber)
                    return false
                currentNumber = nextNumber
                nextNumber = 0
                counting = false
            }
            index++
        }
        return if(counting) nextNumber > currentNumber else true
    }
}