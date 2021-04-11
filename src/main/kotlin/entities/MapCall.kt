package entities

data class MapCall(var expression: String) : Call() {
    init {
        if(">" in expression || "<" in expression || "=" in expression){
            throw(IllegalArgumentException("Not an arithmetic type expression"))
        }
    }

    override fun toString(): String {
        return "map{$expression}"
    }
}