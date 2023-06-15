package com.piriurna.data.mapper

import com.piriurna.data.dto.BookDto
import com.piriurna.data.models.BookEntity
import com.piriurna.domain.models.Book


fun BookDto.toBookEntity(): BookEntity {
    return BookEntity(
        title = volumeInfo.title,
        authors = volumeInfo.authors,
        description = volumeInfo.description,
        thumbnail = volumeInfo.imageLinks?.thumbnail?: "",
        infoLink = volumeInfo.infoLink,
        buyLink = saleInfo.buyLink
    )
}

fun List<BookDto>.toBookEntities(): List<BookEntity> {
    return map { bookDto ->
        bookDto.toBookEntity()
    }
}

fun BookDto.toBook(): Book {
    val volumeInfo = this.volumeInfo
    return Book(
        id = null,
        title = volumeInfo.title,
        authors = volumeInfo.authors,
        description = volumeInfo.description,
        buyLink = saleInfo.buyLink,
        thumbnail = volumeInfo.imageLinks?.thumbnail?: "",
        infoLink = volumeInfo.infoLink,
        isFavorite = false
    )
}

fun List<BookDto>.toBookList(): List<Book> {
    return map { bookDto ->
        bookDto.toBook()
    }
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id.toString(),
        title = title,
        authors = authors,
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
        authors = authors,
        description = description,
        thumbnail = thumbnail,
        infoLink = infoLink,
        buyLink = buyLink,
        isFavorite = isFavorite
    )
}

fun List<Book>.toBookEntities(): List<BookEntity> {
    return map { bookWithAuthors ->
        bookWithAuthors.toBookEntity()
    }
}
