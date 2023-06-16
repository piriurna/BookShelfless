package com.piriurna.bookshelfless.data.mapper

import com.piriurna.bookshelfless.data.dto.BookDto
import com.piriurna.bookshelfless.data.entities.BookEntity
import com.piriurna.bookshelfless.domain.models.Book


fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        title = volumeInfo.title,
        author = volumeInfo.authors.firstOrNull(),
        description = volumeInfo.description,
        thumbnail = volumeInfo.imageLinks?.thumbnail?: "",
        infoLink = volumeInfo.infoLink,
        buyLink = saleInfo.buyLink
    )
}

fun BookDto.toBook(): Book {
    val volumeInfo = this.volumeInfo
    return Book(
        id = null,
        title = volumeInfo.title,
        author = volumeInfo.authors.firstOrNull(),
        description = volumeInfo.description,
        buyLink = saleInfo.buyLink,
        thumbnail = volumeInfo.imageLinks?.thumbnail?: "",
        infoLink = volumeInfo.infoLink,
        isFavorite = false
    )
}
@JvmName("dToToBookList")
fun List<BookDto>.toBookList(): List<Book> {
    return map { bookDto ->
        bookDto.toBook()
    }
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id.toString(),
        title = title?: "Tittle not available",
        author = author,
        description = description,
        buyLink = buyLink,
        thumbnail = thumbnail,
        infoLink = infoLink,
        isFavorite = isFavorite
    )
}

fun List<BookEntity>.toBookList(): List<Book> {
    return map { bookWithAuthors ->
        bookWithAuthors.toBook()
    }
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        title = title,
        author = author,
        description = description,
        thumbnail = thumbnail,
        infoLink = infoLink,
        buyLink = buyLink,
        isFavorite = isFavorite
    )
}
