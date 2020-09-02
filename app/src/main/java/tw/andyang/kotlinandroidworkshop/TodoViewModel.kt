package tw.andyang.kotlinandroidworkshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    val onNewTodo = MutableLiveData<Unit>()

    val todoLiveData: LiveData<List<Todo>> = MediatorLiveData<List<Todo>>().apply {
        addSource(onNewTodo) {
            val todo = Todo.Item("note $count", false)
            this.value = this.value!! + listOf(todo)
            count++
        }
        value = mutableListOf(Todo.Title("This is a title"))
    }

    private var count = 0
}