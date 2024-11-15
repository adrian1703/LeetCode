class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var init = ListNode(-1)
        process(l1, l2, init, false)
        return init.next
    }
    
        fun process (l1: ListNode?, l2: ListNode?, l3: ListNode, overflow: Boolean) {
        if(l1 == null && l2 == null && !overflow){
            return
        }
        if(l1 == null && l2 == null && overflow){
            l3.next = ListNode(1)
            return
        }
        val x = l1?.`val` ?: 0
        val y = l2?.`val` ?: 0
        val sum = x + y + if (overflow) 1 else 0
        val next = ListNode(sum % 10)
        l3.next = next
        return process(l1?.next, l2?.next, next, sum/10 > 0)
    }
}

