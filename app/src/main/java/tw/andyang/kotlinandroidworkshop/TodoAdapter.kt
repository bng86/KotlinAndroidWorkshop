package tw.andyang.kotlinandroidworkshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var todos = listOf<Todo>()

    override fun getItemViewType(position: Int): Int {
        return todos[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Todo.TYPE_TITLE -> TodoTitleViewHolder(parent)
            else -> TodoViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val todo = todos[position]) {
            is Todo.Title -> (holder as TodoTitleViewHolder).bind(todo)
            is Todo.Item -> (holder as TodoViewHolder).bind(todo)
        }
    }

    fun refresh(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }
}

class TodoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {

    private val checkbox: AppCompatCheckBox = itemView.checkbox

    fun bind(todo: Todo.Item) {
        checkbox.text = todo.memo
        checkbox.isChecked = todo.checked
    }

}

class TodoTitleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
) {
    fun bind(todo: Todo.Title) {
        (itemView as AppCompatTextView).text = todo.text
    }
}