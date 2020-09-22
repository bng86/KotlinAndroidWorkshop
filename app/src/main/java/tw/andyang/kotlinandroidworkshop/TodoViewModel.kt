package tw.andyang.kotlinandroidworkshop

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tw.andyang.kotlinandroidworkshop.database.TodoItem
import tw.andyang.kotlinandroidworkshop.repository.TodoItemRepository
import java.util.*

class TodoViewModel(private val repository: TodoItemRepository) : ViewModel() {

    private val title = Todo.Title("This is a title")

    val todoLiveData: LiveData<List<Todo>> = MediatorLiveData<List<Todo>>().apply {
        val source = repository.getTodoItems().map {
            it.map { todoItem ->
                Todo.Item(
                    todoItem.id,
                    todoItem.title,
                    todoItem.done,
                    todoItem.createdAt
                )
            }
        }
        addSource(source) {
            this.value = mutableListOf(title) + it
        }
        value = mutableListOf(title)
    }

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