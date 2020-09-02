package tw.andyang.kotlinandroidworkshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TodoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val todoViewModel = ViewModelProvider(this).get<TodoViewModel>()

        todoViewModel.todoLiveData.observe(this, Observer { todos: List<Todo> ->
            adapter.submitList(todos)
        })

        buttonAdd.setOnClickListener {
            todoViewModel.onNewTodo.postValue(Unit)
        }
    }
}