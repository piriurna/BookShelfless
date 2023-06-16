package com.piriurna.bookshelfless.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "books",
    indices = [Index(value = ["infoLink"], unique = true)]
)
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String?,
    val author: String?,
    val description: String?,
    val buyLink: String?,
    val thumbnail: String?,
    val infoLink: String,
    var isFavorite: Boolean = false
)
