package tw.andyang.kotlinandroidworkshop

sealed class Todo(val viewType: Int) {
    data class Title(val text: String) : Todo(TYPE_TITLE)
    data class Item(
        val memo: String,
        val checked: Boolean
    ) : Todo(TYPE_ITEM)

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_ITEM = 1
    }
}
