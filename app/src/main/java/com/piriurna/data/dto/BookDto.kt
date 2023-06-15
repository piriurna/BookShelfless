package com.piriurna.data.dto

data class BookResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<BookDto>?
)

data class BookDto(
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
    val accessInfo: AccessInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val description: String,
    val imageLinks: ImageLinks?,
    val previewLink: String,
    val infoLink: String
)

data class SaleInfo(
    val saleability: String,
    val buyLink: String?
)

data class AccessInfo(
    val epub: Epub?,
    val pdf: Pdf?,
    val webReaderLink: String
)

data class ImageLinks(
    val thumbnail: String
)

data class Epub(
    val isAvailable: Boolean,
    val acsTokenLink: String
)

data class Pdf(
    val isAvailable: Boolean,
    val acsTokenLink: String
)