package com.piriurna.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "books",
    indices = [Index(value = ["title", "authors"], unique = true)]
)
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val authors: List<String>,
    val description: String,
    val buyLink: String?,
    val thumbnail: String,
    val infoLink: String,
    var isFavorite: Boolean = false
)
