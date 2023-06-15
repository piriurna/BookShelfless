package com.piriurna.data

import com.piriurna.data.dto.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {

    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("maxResults") maxResults: Int,
        @Query("startIndex") startIndex: Int
    ): BookResponse
}
