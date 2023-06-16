package com.piriurna.bookshelfless.domain.usecases

import androidx.paging.PagingData
import com.piriurna.bookshelfless.domain.BooksRepository
import com.piriurna.bookshelfless.domain.models.Book
import kotlinx.coroutines.flow.Flow

class GetBooksPagedUseCase(private val booksRepository: BooksRepository) {

    fun invoke(pageSize: Int = 20): Flow<PagingData<Book>> {
        return booksRepository.getBooksPaged(pageSize)
    }
}