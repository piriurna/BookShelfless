package com.piriurna.bookshelfless.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piriurna.bookshelfless.domain.models.Book
import com.piriurna.bookshelfless.domain.usecases.GetBookDetailUseCase
import com.piriurna.bookshelfless.domain.usecases.UpdateBookFavoriteStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val updateFavoriteStatusUseCase: UpdateBookFavoriteStatusUseCase
) : ViewModel() {

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    fun getBookDetail(bookId: String) {
        viewModelScope.launch {
            _book.value = getBookDetailUseCase.invoke(bookId)
        }

    }

    fun updateFavoriteStatus(bookId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateFavoriteStatusUseCase.invoke(bookId, isFavorite)
            }
        }
    }
}
