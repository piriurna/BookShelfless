package com.piriurna.data

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.piriurna.data.models.BookEntity

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(bookEntity: BookEntity)

    @Transaction
    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBook(bookId: String): BookEntity

    @Transaction
    @Query("SELECT * FROM books")
    suspend fun getBooks(): List<BookEntity>

    @Query("SELECT * FROM books")
    fun getBooksPaged(): PagingSource<Int, BookEntity>

    @Query("SELECT * FROM books WHERE isFavorite = 1")
    fun getFavoriteBooks(): List<BookEntity>

    @Query("UPDATE books SET isFavorite = :isFavorite WHERE id = :id")
    fun updateFavoriteStatus(id: String, isFavorite: Boolean)

}

