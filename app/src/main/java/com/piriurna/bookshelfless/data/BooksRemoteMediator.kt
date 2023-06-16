package com.piriurna.bookshelfless.data

import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.piriurna.bookshelfless.data.entities.BookEntity

import androidx.paging.ExperimentalPagingApi
import com.piriurna.bookshelfless.data.mapper.toBookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class BooksRemoteMediator(
    private val booksApiSource: BooksApiSource,
    private val bookDao: BookDao
) : RemoteMediator<Int, BookEntity>() {

    private var nextIndex: Int? = null

    override suspend fun load(loadType: LoadType, state: PagingState<Int, BookEntity>): MediatorResult {
        // If refreshing or we've reached the end of the data, start over
        if (loadType == LoadType.REFRESH || nextIndex == null) {
            nextIndex = 0
        }

        return try {
            nextIndex?.let { index ->
                val books = booksApiSource.fetchBooks(startIndex = index)
                withContext(Dispatchers.IO) {
                    books.forEach {
                        bookDao.insertBook(it.toBookEntity())
                    }
                }
                // Update the next index, increment by state.config.pageSize
                nextIndex = index + state.config.pageSize

                MediatorResult.Success(endOfPaginationReached = books.isEmpty())
            } ?: MediatorResult.Success(endOfPaginationReached = true)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }
}

