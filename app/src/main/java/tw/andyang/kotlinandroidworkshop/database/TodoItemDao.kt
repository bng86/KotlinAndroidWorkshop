package tw.andyang.kotlinandroidworkshop.database

import androidx.room.Query

@Dao
interface TodoItemDao: GenericDao<TodoItem> {

    @Query("SELECT * FROM ${TodoItem.TABLE_NAME} ORDER BY ${TodoItem.COLUMN_CREATED_AT} DESC")
    fun findAll(): List<TodoItemDao>
}