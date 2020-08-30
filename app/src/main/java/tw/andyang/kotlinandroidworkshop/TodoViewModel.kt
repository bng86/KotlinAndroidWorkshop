package tw.andyang.kotlinandroidworkshop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {

    val todoLiveData =  MutableLiveData<List<Todo>>(
        mutableListOf(Todo.Title("This is a title"))
    )

    private var count = 0

    fun addNewTodo() {
        val todo = Todo.Item("note $count", false)
        todoLiveData.value = todoLiveData.value!! + listOf(todo)
        count ++
    }
}