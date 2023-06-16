package com.piriurna.bookshelfless.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.bookshelfless.data.entities.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
}
