package com.piriurna.bookshelfless.domain.usecases

import com.piriurna.bookshelfless.domain.BooksRepository
import com.piriurna.bookshelfless.domain.models.Book

class UpdateLocalBooksUseCase(
    private val booksRepository: BooksRepository
) {

    suspend fun invoke(): List<Book> {
        val booksFromApi = booksRepository.fetchBooks()

        booksFromApi.forEach { apiBook ->
            // If the book doesn't exist in the local database, it will be inserted.
            // If it does exist, nothing will happen (the existing record won't be changed).
            booksRepository.insertBook(apiBook)
        }

        return booksRepository.getLocalBooks()
    }
}