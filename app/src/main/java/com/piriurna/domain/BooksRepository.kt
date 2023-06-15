package com.piriurna.domain

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.piriurna.data.models.BookEntity
import com.piriurna.domain.models.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun fetchBooks(): List<Book>

    suspend fun insertBook(book: Book)

    suspend fun getLocalBooks(): List<Book>

    fun getFavoriteBooks(): List<Book>

    fun updateFavoriteStatus(id: String, isFavorite: Boolean)

    fun getBooksPaged(pageSize: Int): Flow<PagingData<Book>>
}