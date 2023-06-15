package com.piriurna.data

import com.piriurna.data.dto.BookDto
import javax.inject.Inject

class BooksApiSource @Inject constructor(
    private val booksApiService: BooksApiService
) {

    suspend fun fetchBooks() : List<BookDto> {
        return booksApiService.getBooks(MAX_RESULTS, START_INDEX).items ?: emptyList()
    }

    companion object {
        const val MAX_RESULTS = 40
        const val START_INDEX = 0
    }
}