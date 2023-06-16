package com.piriurna.bookshelfless.presentation.di

import com.piriurna.bookshelfless.presentation.ui.details.BookDetailFragment
import com.piriurna.bookshelfless.presentation.ui.home.BooksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeBooksFragment(): BooksFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeBookDetailFragment(): BookDetailFragment

}