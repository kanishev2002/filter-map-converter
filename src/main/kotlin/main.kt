import entities.Call
import entities.FilterCall
import entities.MapCall

fun main() {
    val chain = readLine()!!
    val expressions = chain.split("%>%")
    val parsed: List<Call>
    try {
        parsed = expressions.map {
            if (it.startsWith("filter")) {
                FilterCall(
                    mutableListOf(
                        it.substring(
                            it.indexOf('('),
                            it.lastIndexOf(')') + 1
                        )
                    )
                )
            } else {
                MapCall(
                    it.substring(
                        it.indexOf('{') + 1,
                        it.lastIndexOf('}')
                    )
                )
            }
        }
    } catch (e: StringIndexOutOfBoundsException) {
        println("SYNTAX ERROR")
        return
    } catch (e: IllegalArgumentException) {
        println("TYPE ERROR")
        return
    }

    val result = mutableListOf(parsed.first())

    for (call in parsed.drop(1)) {
        if (call is FilterCall && result.last() is FilterCall) {
            (result.last() as FilterCall) += call
        } else if (call is FilterCall && result.last() is MapCall ||
            call is MapCall && result.last() is FilterCall
        ) {
            result.add(call)
        } else {
            result.add(FilterCall(mutableListOf("""("element"="element")""")))
            result.add(call)
        }
    }
    if (result.last() !is MapCall) {
        result.add(MapCall("(element)"))
    }
    if (result.first() !is FilterCall) {
        result.add(0, FilterCall(mutableListOf("(element=element)")))
    }
    println(result.joinToString("%>%"))
}



