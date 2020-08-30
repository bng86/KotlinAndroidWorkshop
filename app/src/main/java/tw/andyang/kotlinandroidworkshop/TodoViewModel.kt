package tw.andyang.kotlinandroidworkshop

import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {

    var todos = listOf<Todo>(Todo.Title("This is a title"))
        private set

    private var count = 0

    fun addNewTodo() {
        val todo = Todo.Item("note $count", false)
        todos = todos.toMutableList().apply {
            add(todo)
        }
        count ++
    }
}