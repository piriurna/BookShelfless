package com.piriurna.bookshelfless.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.piriurna.bookshelfless.data.mapper.toBook
import com.piriurna.bookshelfless.data.mapper.toBookEntity
import com.piriurna.bookshelfless.data.mapper.toBookList
import com.piriurna.bookshelfless.domain.BooksRepository
import com.piriurna.bookshelfless.domain.models.Book
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

    override fun getFavoriteBooksPaged(pageSize: Int): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { bookDao.getFavoriteBooksPaged() }
        ).flow.map { pagingData ->
            pagingData.map { bookEntity ->
                bookEntity.toBook()
            }
        }
    }

    override fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
        bookDao.updateFavoriteStatus(id, isFavorite)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getBooksPaged(pageSize: Int): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            remoteMediator = BooksRemoteMediator(booksApiSource, bookDao),
            pagingSourceFactory = { bookDao.getBooksPaged() }
        ).flow.map { pagingData ->
            pagingData.map { bookEntity ->
                bookEntity.toBook()
            }
        }
    }

    override suspend fun getBook(id: String): Book {
        return bookDao.getBook(id).toBook()
    }
}
