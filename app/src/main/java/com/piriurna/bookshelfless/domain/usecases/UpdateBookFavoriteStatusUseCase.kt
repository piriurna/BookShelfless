package com.piriurna.bookshelfless.domain.usecases

import com.piriurna.bookshelfless.domain.BooksRepository
import javax.inject.Inject

class UpdateBookFavoriteStatusUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    fun invoke(bookId: String, isFavorite: Boolean) {
        booksRepository.updateFavoriteStatus(bookId, isFavorite)
    }
}