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
import kotlinx.android.synthetic.main.fragment_add_todo.*

class AddTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTodo.requestFocus()

        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )

        val todoViewModel = ViewModelProvider(requireActivity()).get(TodoViewModel::class.java)

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