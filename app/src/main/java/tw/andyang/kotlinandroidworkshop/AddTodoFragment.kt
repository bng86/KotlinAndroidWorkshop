package tw.andyang.kotlinandroidworkshop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_todo.*
import tw.andyang.kotlinandroidworkshop.database.AppDatabase
import tw.andyang.kotlinandroidworkshop.mvvm.AnyViewModelFactory
import tw.andyang.kotlinandroidworkshop.repository.TodoItemRepository

class AddTodoFragment : Fragment() {

    private val args by navArgs<AddTodoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // auto open soft keyboard
        editTodo.requestFocus()
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )

        // setup argument
        editTodo.setText(args.memo)
        editTodo.setSelection(args.memo.length)

        val todoItemDb = AppDatabase.getInstance(requireActivity().applicationContext)
        val todoItemRepo = TodoItemRepository(todoItemDb)
        val viewModelFactory = AnyViewModelFactory {
            TodoViewModel(todoItemRepo)
        }
        val todoViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(TodoViewModel::class.java)

        buttonAdd.setOnClickListener {
            if (editTodo.text.isNullOrEmpty()) {
                editTodo.error = "請輸入你的代辦事項"
            } else {
                // clear error
                editTodo.error = null
                // post data to view model
                todoViewModel.onNewTodo.postValue(editTodo.text.toString())
                // hide soft keyboard when item added
                view.clearFocus()
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                // back to list page
                findNavController().popBackStack()
            }
        }
    }
}