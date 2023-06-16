package com.piriurna.bookshelfless.domain.usecases

import com.piriurna.bookshelfless.domain.BooksRepository
import com.piriurna.bookshelfless.domain.models.Book
import javax.inject.Inject

class GetBookDetailUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {

    suspend fun invoke(bookId: String): Book {
        return booksRepository.getBook(bookId)
    }
}