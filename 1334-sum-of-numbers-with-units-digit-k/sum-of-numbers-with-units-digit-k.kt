class Solution {
   fun minimumNumbers(num: Int, k: Int): Int {
       if (num == 0)
           return 0
       val target = num % 10
       for (i in 1..10) {
           val min = ( i * k )
           val digit = min % 10
           if (target == digit && num >= min){
               return i
           }

       }
       return -1
   }
}