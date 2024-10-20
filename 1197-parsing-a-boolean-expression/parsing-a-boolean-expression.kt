class Solution {
        fun parseBoolExpr(expression: String): Boolean {
        val rootComp: InterpreterComponent = PassComponent()
        var currentComp = rootComp
        var lastCreated = rootComp
        var i = 0
        while (i < expression.length) {
            val c = expression[i]
            when (c) {
                ',' -> { }
                ')' -> currentComp = currentComp.getParent()!!
                '(' -> currentComp = lastCreated
                else -> {
                    lastCreated = AbstractComponent.evalChar(expression[i])
                    currentComp.addChild(lastCreated)
                    lastCreated.setParent(currentComp)
                }
            }
            i++
        }
        return rootComp.doOperation()
    }

}



interface InterpreterComponent {
    fun doOperation():Boolean
    fun getChildren(): MutableList<InterpreterComponent>
    fun addChild(c: InterpreterComponent)
    fun setParent(c: InterpreterComponent)
    fun getParent(): InterpreterComponent?
}

abstract class AbstractComponent: InterpreterComponent {

    companion object {
        fun evalChar(c: Char): InterpreterComponent {
            return when (c) {
                't'  -> ValueComponent(true)
                'f'  -> ValueComponent(false)
                '!'  -> NotComponent()
                '&'  -> AndComponent()
                '|'  -> OrComponent()
                else -> throw IllegalArgumentException("Invalid character: $c")
            }
        }
    }

    private val children = mutableListOf<InterpreterComponent>()
    private var parent: InterpreterComponent? = null

    override fun getChildren(): MutableList<InterpreterComponent> {
        return children
    }
    override fun addChild(c: InterpreterComponent) {
        children.add(c)
    }

    override fun setParent(c: InterpreterComponent) {
        parent = c
    }

    override fun getParent(): InterpreterComponent? {
        return parent
    }
}

class PassComponent: AbstractComponent() {
    override fun doOperation(): Boolean {
        return getChildren()[0].doOperation()
    }

}

class NotComponent: AbstractComponent(), InterpreterComponent {
    override fun doOperation(): Boolean {
        return getChildren()[0].doOperation().not()
    }
}

class AndComponent : AbstractComponent(), InterpreterComponent {
    override fun doOperation(): Boolean {
        return getChildren().map { it.doOperation() }.reduce { a, b -> a.and(b) }
    }
}

class OrComponent: AbstractComponent(), InterpreterComponent {
    override fun doOperation(): Boolean {
        return getChildren().map { it.doOperation() }.reduce { a,b -> a.or(b) }
    }
}

class ValueComponent(private val value: Boolean): AbstractComponent(), InterpreterComponent {
    override fun doOperation(): Boolean {
        return value
    }
}