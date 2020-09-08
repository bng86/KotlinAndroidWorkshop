package tw.andyang.kotlinandroidworkshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    val onNewTodo = MutableLiveData<String>()

    val todoLiveData: LiveData<List<Todo>> = MediatorLiveData<List<Todo>>().apply {
        addSource(onNewTodo) { text ->
            val todo = Todo.Item(text, false)
            this.value = this.value!! + listOf(todo)
        }
        value = mutableListOf(Todo.Title("This is a title"))
    }
}