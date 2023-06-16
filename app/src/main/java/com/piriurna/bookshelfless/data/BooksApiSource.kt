package com.piriurna.bookshelfless.data

import com.piriurna.bookshelfless.data.dto.BookDto
import javax.inject.Inject

class BooksApiSource @Inject constructor(
    private val booksApiService: BooksApiService
) {

    suspend fun fetchBooks(query: String = "android", startIndex: Int = 0) : List<BookDto> {
        return booksApiService.getBooks(query, MAX_RESULTS, startIndex).items ?: emptyList()
    }

    companion object {
        const val MAX_RESULTS = 20
    }
}