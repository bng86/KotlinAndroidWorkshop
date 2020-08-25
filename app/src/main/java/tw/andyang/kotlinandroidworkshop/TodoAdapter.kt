package tw.andyang.kotlinandroidworkshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter : RecyclerView.Adapter<TodoViewHolder>() {

    private var todos = listOf<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.checkbox.text = todo.memo
        holder.checkbox.isChecked = todo.checked
    }

    fun refresh(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }
}

class TodoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {
    val checkbox: AppCompatCheckBox = itemView.checkbox
}