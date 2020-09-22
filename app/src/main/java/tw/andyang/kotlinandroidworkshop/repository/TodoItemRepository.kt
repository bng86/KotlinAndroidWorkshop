package tw.andyang.kotlinandroidworkshop.repository

import androidx.lifecycle.LiveData
import tw.andyang.kotlinandroidworkshop.database.AppDatabase
import tw.andyang.kotlinandroidworkshop.database.TodoItem

class TodoItemRepository(
    private val database: AppDatabase
) {
}