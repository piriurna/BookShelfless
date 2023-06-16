package com.piriurna.bookshelfless.data

import com.piriurna.bookshelfless.data.dto.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {

    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("startIndex") startIndex: Int
    ): BookResponse
}
