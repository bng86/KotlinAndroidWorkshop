package tw.andyang.kotlinandroidworkshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        adapter.refresh(
            listOf(
                Todo.Title(getString(R.string.todo_list_title)),
                Todo.Item(
                    "hello world! hello world! hello world! hello world! hello world!",
                    false
                ),
                Todo.Item("world", false),
                Todo.Item(
                    "hello world! hello world! hello world! hello world! hello world!",
                    false
                ),
                Todo.Item("hello", false),
                Todo.Item("hello", false),
                Todo.Item(
                    "hello world! hello world! hello world! hello world! hello world!",
                    false
                ),
                Todo.Item("hello", false),
                Todo.Item("hello", false),
                Todo.Item("hello", false),
                Todo.Item("world", false)
            )
        )
    }
}