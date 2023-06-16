package com.piriurna.bookshelfless.presentation.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.piriurna.bookshelfless.R
import com.piriurna.bookshelfless.databinding.FragmentBookDetailBinding
import com.piriurna.bookshelfless.domain.models.Book
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BookDetailFragment : DaggerFragment(R.layout.fragment_book_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BookDetailViewModel by viewModels() { viewModelFactory  }

    private lateinit var _binding: FragmentBookDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookDetailBinding.bind(view)
        val bookId = arguments?.getString("bookId")
        if (bookId != null) {
            viewModel.getBookDetail(bookId)
        }
        viewModel.book.observe(viewLifecycleOwner) { book ->
            updateUI(book, view)
        }
    }

    private fun updateUI(book: Book, view: View) {
        _binding.bookTitle.text = book.title

        Glide.with(view)
            .load(book.thumbnail)
            .into(_binding.bookCover)

        _binding.bookAuthor.text = book.author
        _binding.bookDescription.text = book.description
        _binding.favoriteButton.isEnabled = !book.isFavorite
        _binding.buyButton.isEnabled = book.buyLink != null
        if(!_binding.buyButton.hasOnClickListeners()) {
            _binding.buyButton.setOnClickListener {
                openWebPage(book.buyLink)
            }
        }
        if(!_binding.favoriteButton.hasOnClickListeners()) {
            _binding.favoriteButton.setOnClickListener {
                book.id?.let { bookId ->
                    viewModel.updateFavoriteStatus(bookId, true)
                    _binding.favoriteButton.isEnabled = false
                }
            }
        }
    }

    private fun openWebPage(url: String?) {
        if (url != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}
