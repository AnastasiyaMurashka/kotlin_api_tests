package bo

class Resource {
    private var id: Int = 0
    private lateinit var name: String
    private var year = 0
    private lateinit var color: String
    private lateinit var pantone_value: String

    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getYear(): Int {
        return year
    }

    fun getColor(): String {
        return color
    }

    fun getPantoneValue(): String {
        return pantone_value
    }
}