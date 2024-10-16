class Bank(balance: LongArray) {
    private val accounts: LongArray = balance
    private var numberOfAccounts = accounts.size

    fun transfer(account1: Int, account2: Int, money: Long): Boolean {
                val balance1 = canWithdraw(account1, money)?:return false
        val balance2 = canDeposit(account2, money)?:return false
        if(account1 == account2)
            return true
        doSetAccountBalance(account1, balance1)
        doSetAccountBalance(account2, balance2)
        return true
    }

    fun deposit(account: Int, money: Long): Boolean {
        val balance = canDeposit(account, money) ?: return false
        doSetAccountBalance(account, balance)
        return true
    }
    private fun canDeposit(account: Int, money: Long): Long? {
        if (money < 0 )
            return null
        val balance = getAccountBalance(account)?:return null
        return balance + money
    }

    fun withdraw(account: Int, money: Long): Boolean {
        val balance = canWithdraw(account, money) ?: return false
        doSetAccountBalance(account, balance)
        return true
    }

    private fun doSetAccountBalance(account: Int, balance: Long) {
        accounts[account-1] = balance
    }

    private fun canWithdraw(account: Int, money: Long): Long? {
        if (money < 0 )
            return null
        var balance = getAccountBalance(account) ?: return null
        balance -= money
        if (balance < 0)
            return null
        return balance
    }

    private fun getAccountBalance(account: Int): Long? {
        return if (account in 1..numberOfAccounts)
            accounts[account-1] else null
    }

}

/**
 * Your Bank object will be instantiated and called as such:
 * var obj = Bank(balance)
 * var param_1 = obj.transfer(account1,account2,money)
 * var param_2 = obj.deposit(account,money)
 * var param_3 = obj.withdraw(account,money)
 */