package tw.andyang.kotlinandroidworkshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_todo_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tw.andyang.kotlinandroidworkshop.database.AppDatabase
import tw.andyang.kotlinandroidworkshop.mvvm.AnyViewModelFactory
import tw.andyang.kotlinandroidworkshop.network.ApiClient
import tw.andyang.kotlinandroidworkshop.repository.TodoItemRepository

class TodoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val todoItemRepo = TodoItemRepository(todoItemDb)
        val viewModelFactory = AnyViewModelFactory {
            TodoViewModel(todoItemRepo)
        }
        val todoViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TodoViewModel::class.java)

        val adapter = TodoAdapter().apply {
            onTodoChangeListener = object : OnTodoChangeListener {
                override fun onChange(todo: Todo.Item) {
                    todoViewModel.updateTodo(todo)
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        todoViewModel.todoLiveData.observe(viewLifecycleOwner, Observer { todos: List<Todo> ->
            adapter.submitList(todos)
        })

        buttonAdd.setOnClickListener {
            findNavController().navigate(TodoListFragmentDirections.actionMainFragmentToAddTodoFragment())
        }


        GlobalScope.launch(Dispatchers.Main) {
            val gson = Gson()
            val client = ApiClient()
            Log.d("Hello", gson.toJson(client.listUser()))
            val userNames = client.listUser().map { it.lastName }
            activity?.run {
                Toast.makeText(this, userNames.joinToString(", "), Toast.LENGTH_LONG).show()
            }
        }
    }
}