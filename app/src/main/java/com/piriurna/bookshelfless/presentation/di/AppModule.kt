package com.piriurna.bookshelfless.presentation.di

import com.piriurna.bookshelfless.data.BookDao
import com.piriurna.bookshelfless.data.BookRepositoryImpl
import com.piriurna.bookshelfless.data.BooksApiSource
import com.piriurna.bookshelfless.domain.BooksRepository
import com.piriurna.bookshelfless.domain.usecases.GetBookDetailUseCase
import com.piriurna.bookshelfless.domain.usecases.GetBooksPagedUseCase
import com.piriurna.bookshelfless.domain.usecases.GetFavoriteBooksPagedUseCase
import com.piriurna.bookshelfless.domain.usecases.UpdateBookFavoriteStatusUseCase
import com.piriurna.bookshelfless.domain.usecases.UpdateLocalBooksUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideBooksRepository(
        bookDao: BookDao,
        booksApiSource: BooksApiSource
    ): BooksRepository {
        return BookRepositoryImpl(booksApiSource, bookDao)
    }

    @Provides
    @Singleton
    fun provideGetBooksPagedUseCase(booksRepository: BooksRepository): GetBooksPagedUseCase {
        return GetBooksPagedUseCase(booksRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateLocalBooksUseCase(booksRepository: BooksRepository): UpdateLocalBooksUseCase {
        return UpdateLocalBooksUseCase(booksRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateBookFavoriteStatusUseCase(booksRepository: BooksRepository): UpdateBookFavoriteStatusUseCase {
        return UpdateBookFavoriteStatusUseCase(booksRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteBooksPagedUseCase(booksRepository: BooksRepository): GetFavoriteBooksPagedUseCase {
        return GetFavoriteBooksPagedUseCase(booksRepository)
    }

    @Provides
    @Singleton
    fun provideGetBookDetailUseCase(booksRepository: BooksRepository): GetBookDetailUseCase {
        return GetBookDetailUseCase(booksRepository)
    }
}
