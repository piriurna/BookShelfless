package com.piriurna.bookshelfless.presentation.di

import android.content.Context
import androidx.room.Room
import com.piriurna.bookshelfless.data.BookDao
import com.piriurna.bookshelfless.data.BookDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideBookDatabase(context: Context): BookDatabase {
        return Room.databaseBuilder(
            context,
            BookDatabase::class.java, "book-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookDao(database: BookDatabase): BookDao {
        return database.bookDao()
    }
}
