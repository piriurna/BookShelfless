package com.piriurna.bookshelfless.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piriurna.bookshelfless.presentation.ui.details.BookDetailViewModel
import com.piriurna.bookshelfless.presentation.ui.home.BooksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindBooksViewModel(viewModel: BooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    abstract fun bindBookDetailViewModel(viewModel: BookDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
