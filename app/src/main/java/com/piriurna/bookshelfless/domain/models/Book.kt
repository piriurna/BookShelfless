package com.piriurna.bookshelfless.domain.models

data class Book(
    val id: String?,
    val title: String,
    val author: String?,
    val description: String?,
    val thumbnail: String?,
    val buyLink: String?,
    val infoLink: String,
    val isFavorite: Boolean = false
) {
}