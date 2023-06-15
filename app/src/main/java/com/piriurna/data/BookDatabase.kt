package com.piriurna.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.piriurna.data.models.AuthorEntity
import com.piriurna.data.models.BookEntity

@Database(entities = [BookEntity::class, AuthorEntity::class, BookAuthorCrossRef::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                BookDatabase::class.java, "Book.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
