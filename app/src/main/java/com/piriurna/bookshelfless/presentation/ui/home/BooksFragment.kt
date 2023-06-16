package com.piriurna.bookshelfless.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.piriurna.bookshelfless.R
import com.piriurna.bookshelfless.databinding.FragmentBooksBinding
import com.piriurna.bookshelfless.domain.models.Book
import com.piriurna.bookshelfless.presentation.ui.home.adapters.BooksAdapter
import com.piriurna.bookshelfless.presentation.ui.navigation.NavigationConstants.BOOK_ID
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class BooksFragment : DaggerFragment(R.layout.fragment_books) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BooksViewModel by viewModels() { viewModelFactory  }
    private val booksAdapter = BooksAdapter(
        onBookClicked = ::onBookClicked,
        onFavoriteClicked = ::onFavoriteClicked
    )

    private lateinit var _binding: FragmentBooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBooksBinding.bind(view)
        initAdapter()
        fetchBooks()
        observeFavorites()
        _binding.favoriteButton.setOnClickListener {
            viewModel.toggleShowOnlyFavorites()
        }
    }

    private fun initAdapter() {
        _binding.booksRecyclerView.adapter = booksAdapter

    }

    private fun fetchBooks() {
        viewModel.books.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                booksAdapter.submitData(pagingData)
            }
        }
    }

    private fun observeFavorites() {
        viewModel.showOnlyFavorites.observe(viewLifecycleOwner) { showOnlyFavorites ->
            _binding.favoriteButton.text = if (showOnlyFavorites) {
                getString(R.string.show_all)
            } else {
                getString(R.string.show_favorites)
            }
        }
    }


    private fun onBookClicked(book: Book) {
        val bundle = bundleOf(BOOK_ID to book.id)
        findNavController().navigate(R.id.action_navigation_home_to_bookDetailFragment, bundle)
    }

    private fun onFavoriteClicked(book: Book) {
        if(book.id != null) {
            viewModel.updateFavoriteStatus(book.id, !book.isFavorite)
        }
    }
}
