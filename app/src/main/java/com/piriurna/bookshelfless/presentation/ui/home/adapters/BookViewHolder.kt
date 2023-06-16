package com.piriurna.bookshelfless.presentation.ui.home.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piriurna.bookshelfless.R
import com.piriurna.bookshelfless.domain.models.Book

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val bookCoverImage: ImageView = view.findViewById(R.id.bookCoverImage)
    private val bookTitle: TextView = view.findViewById(R.id.bookTitle)
    private val bookAuthors: TextView = view.findViewById(R.id.bookAuthors)
    val favoriteIcon: ImageView = view.findViewById(R.id.favoriteIcon)

    fun bind(book: Book) {
        Glide.with(itemView)
            .load(book.thumbnail)
            .into(bookCoverImage)

        bookTitle.text = book.title
        bookAuthors.text = book.author
        favoriteIcon.setImageResource(if (book.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border)
    }
}
