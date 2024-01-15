package com.caiosilva.turbitest.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.caiosilva.turbitest.MainActivity
import com.caiosilva.turbitest.R
import com.caiosilva.turbitest.databinding.FragmentComicsDetailsBinding
import com.caiosilva.turbitest.util.Constants.COMIC_ID
import com.caiosilva.turbitest.util.view.hideProgress
import com.caiosilva.turbitest.util.view.loadImageWithUrl
import com.caiosilva.turbitest.util.view.showProgress
import com.caiosilva.turbitest.view.viewmodel.ComicDetailsViewModel
import com.caiosilva.turbitest.view.viewstates.ComicDetailsViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicDetailsFragment : Fragment() {

    private var _binding: FragmentComicsDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComicDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicsDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(COMIC_ID)?.let { comicId ->
            viewModel.getComicDetails(comicId)
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.comicDetailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ComicDetailsViewState.OnLoading -> {
                    (activity as? MainActivity)?.showProgress()
                }

                is ComicDetailsViewState.OnError -> Toast.makeText(
                    requireContext(),
                    it.error,
                    Toast.LENGTH_SHORT
                ).show()

                is ComicDetailsViewState.OnSuccess ->
                    setupViews(it).also { (activity as? MainActivity)?.hideProgress() }
            }
        }
    }

    private fun setupViews(viewState: ComicDetailsViewState.OnSuccess) {
        with(binding) {
            viewState.comic.data?.results?.firstOrNull()?.let { comic ->
                ivComicCover.loadImageWithUrl(comic.thumbnail?.path + "." + comic.thumbnail?.extension)
                tvComicTitle.text = getString(R.string.title_text_view, comic.title)
                tvComicPublicationDate.text = getString(
                    R.string.publication_date_text_view,
                    comic.dates?.firstOrNull()?.date.orEmpty()
                )
                tvComicAuthors.text = getString(
                    R.string.authors_text_view,
                    comic.creators?.items?.joinToString(", ") { creator ->
                        creator.name.orEmpty()
                    }
                )
                tvComicPrice.text = getString(
                    R.string.price_text_view,
                    comic.prices?.firstOrNull()?.price.toString()
                )
                tvComicDescription.text =
                    if (comic.description.isNullOrBlank())
                        getString(R.string.no_description)
                    else
                        getString(
                            R.string.description_text_view,
                            comic.description
                        )
            }
        }
    }
}