package navigation

class Bundle {

    private val args: MutableMap<String, Any?> = mutableMapOf()

    fun put(tag: String, value: Any?) {
        args[tag] = value
    }

    fun getInt(tag: String): Int? {
        val value = args[tag]
        if (value !is Int?) throw IllegalArgumentException("Value $value is not Int?")
        return value
    }

    fun getString(tag: String): String? {
        val value = args[tag]
        if (value !is String?) throw IllegalArgumentException("Value $value is not Int?")
        return value
    }

    fun getAny(tag: String): Any? = args[tag]
}