package com.piriurna.bookshelfless.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.piriurna.bookshelfless.domain.models.Book
import com.piriurna.bookshelfless.domain.usecases.GetBooksPagedUseCase
import com.piriurna.bookshelfless.domain.usecases.GetFavoriteBooksPagedUseCase
import com.piriurna.bookshelfless.domain.usecases.UpdateBookFavoriteStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksViewModel @Inject constructor(
    private val getBooksPagedUseCase: GetBooksPagedUseCase,
    private val updateFavoriteStatusUseCase: UpdateBookFavoriteStatusUseCase,
    private val getFavoriteBooksUseCase: GetFavoriteBooksPagedUseCase
) : ViewModel() {

    private val _showOnlyFavorites = MutableLiveData(false)
    val showOnlyFavorites: LiveData<Boolean> = _showOnlyFavorites

    val books: LiveData<PagingData<Book>> = _showOnlyFavorites.switchMap { showFavorites ->
        if (showFavorites) {
            getFavoriteBooksUseCase.invoke().asLiveData()
        } else {
            getBooksPagedUseCase.invoke().asLiveData()
        }.cachedIn(viewModelScope)
    }

    fun updateFavoriteStatus(bookId: String, isFavorite: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updateFavoriteStatusUseCase.invoke(bookId, isFavorite)
            }
        }
    }

    fun toggleShowOnlyFavorites() {
        _showOnlyFavorites.value = _showOnlyFavorites.value != true
    }
}
