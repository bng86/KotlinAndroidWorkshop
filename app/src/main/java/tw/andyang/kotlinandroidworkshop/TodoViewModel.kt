package tw.andyang.kotlinandroidworkshop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import tw.andyang.kotlinandroidworkshop.database.TodoItem
import tw.andyang.kotlinandroidworkshop.repository.TodoItemRepository
import java.util.*

class TodoViewModel(private val repository: TodoItemRepository) : ViewModel() {

    val onNewTodo = MutableLiveData<String>()

    val todoLiveData: LiveData<List<Todo>> = MediatorLiveData<List<Todo>>().apply {
        addSource(onNewTodo) { text ->
            val todo = Todo.Item(text, false)
            this.value = this.value!! + listOf(todo)
        }
        value = mutableListOf(Todo.Title("This is a title"))

    fun createNewTodo(title: String) {
        val todoItem = TodoItem(
            title = title,
            done = false,
            createdAt = Date()
        )
        viewModelScope.launch {
            repository.insertTodoItem(todoItem)
        }
    }
}