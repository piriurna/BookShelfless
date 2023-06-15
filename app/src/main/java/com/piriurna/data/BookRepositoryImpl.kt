package com.piriurna.data

import androidx.paging.DataSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import com.piriurna.data.mapper.toBook
import com.piriurna.data.mapper.toBookEntity
import com.piriurna.data.mapper.toBookList
import com.piriurna.data.models.BookEntity
import com.piriurna.domain.BooksRepository
import com.piriurna.domain.models.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val booksApiSource: BooksApiSource,
    private val bookDao: BookDao
): BooksRepository {
    override suspend fun fetchBooks(): List<Book> {
        return booksApiSource.fetchBooks().toBookList()
    }

    override suspend fun insertBook(book: Book) {
        bookDao.insertBook(book.toBookEntity())
    }

    override suspend fun getLocalBooks(): List<Book> {
        return bookDao.getBooks().toBookList()
    }

    override fun getFavoriteBooks(): List<Book> {
        return bookDao.getFavoriteBooks().toBookList()
    }

    override fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
        bookDao.updateFavoriteStatus(id, isFavorite)
    }

    override fun getBooksPaged(pageSize: Int): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { bookDao.getBooksPaged() }
        ).flow.map { pagingData ->
            pagingData.map { bookEntity ->
                bookEntity.toBook()
            }
        }
    }
}
