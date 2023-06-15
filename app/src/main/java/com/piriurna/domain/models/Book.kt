package com.piriurna.domain.models

data class Book(
    val id: String?,
    val title: String,
    val authors: List<String>,
    val description: String,
    val thumbnail: String,
    val buyLink: String?,
    val infoLink: String,
    val isFavorite: Boolean = false
) {
}