package tw.andyang.kotlinandroidworkshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TodoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager( 3, GridLayoutManager.VERTICAL)

        adapter.refresh(
            listOf(
                Todo("hello world! hello world! hello world! hello world! hello world!", false),
                Todo("world", false),
                Todo("hello world! hello world! hello world! hello world! hello world!", false),
                Todo("hello", false),
                Todo("hello", false),
                Todo("hello world! hello world! hello world! hello world! hello world!", false),
                Todo("hello", false),
                Todo("hello", false),
                Todo("hello", false),
                Todo("world", false)
            )
        )
    }
}