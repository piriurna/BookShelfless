package com.piriurna.bookshelfless.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.piriurna.bookshelfless.R
import com.piriurna.bookshelfless.domain.models.Book

class BooksAdapter(private val onBookClicked: (Book) -> Unit, private val onFavoriteClicked: (Book) -> Unit) :
    PagingDataAdapter<Book, BookViewHolder>(BOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view).apply {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) onBookClicked(item)
                }
            }
            favoriteIcon.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) onFavoriteClicked(item)
                }
            }


        }
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        if (book != null) {
            holder.bind(book)
        }
    }

    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
        }
    }
}
