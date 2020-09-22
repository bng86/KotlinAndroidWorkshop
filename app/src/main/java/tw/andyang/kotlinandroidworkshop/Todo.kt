package tw.andyang.kotlinandroidworkshop

import java.util.*

sealed class Todo(val viewType: Int) {
    data class Title(val text: String) : Todo(TYPE_TITLE)
    data class Item(
        val id: Int,
        val memo: String,
        val checked: Boolean,
        val createdAt: Date
    ) : Todo(TYPE_ITEM)

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_ITEM = 1
    }
}
