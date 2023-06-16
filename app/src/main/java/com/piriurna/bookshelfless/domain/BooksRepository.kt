package com.piriurna.bookshelfless.domain

import androidx.paging.PagingData
import com.piriurna.bookshelfless.domain.models.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun fetchBooks(): List<Book>

    suspend fun insertBook(book: Book)

    suspend fun getLocalBooks(): List<Book>

    fun getFavoriteBooks(): List<Book>

    fun getFavoriteBooksPaged(pageSize: Int): Flow<PagingData<Book>>

    fun updateFavoriteStatus(id: String, isFavorite: Boolean)

    fun getBooksPaged(pageSize: Int): Flow<PagingData<Book>>

    suspend fun getBook(id: String): Book
}