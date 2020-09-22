package tw.andyang.kotlinandroidworkshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter : ListAdapter<Todo, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.viewType == newItem.viewType
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
) {

    var onTodoChangeListener: OnTodoChangeListener? = null

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Todo.TYPE_TITLE -> TodoTitleViewHolder(parent)
            else -> TodoViewHolder(parent, onTodoChangeListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val todo = getItem(position)) {
            is Todo.Title -> (holder as TodoTitleViewHolder).bind(todo)
            is Todo.Item -> (holder as TodoViewHolder).bind(todo)
        }
    }
}

class TodoViewHolder(parent: ViewGroup, private val onTodoChangeListener: OnTodoChangeListener?) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
) {

    private val checkbox: AppCompatCheckBox = itemView.checkbox

    fun bind(todo: Todo.Item) {
        checkbox.text = todo.memo
        checkbox.isChecked = todo.checked
        checkbox.setOnClickListener { view ->
            onTodoChangeListener?.onChange(Todo.Item(todo.id, todo.memo, !todo.checked, todo.createdAt))
        }
    }

}

class TodoTitleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
) {
    fun bind(todo: Todo.Title) {
        (itemView as AppCompatTextView).text = todo.text
    }
}