package entities

data class FilterCall(var conditions: MutableList<String>) : Call() {
    init {
        conditions.forEach {
            if("<" !in it && ">" !in it && "=" !in it){
                throw(IllegalArgumentException("Not a boolean type expression"))
            }
        }
    }

    operator fun plusAssign(other: FilterCall) {
        conditions.addAll(other.conditions)
    }

    override fun toString(): String {
        return "filter{" + conditions.joinToString("&") + "}"
    }
}