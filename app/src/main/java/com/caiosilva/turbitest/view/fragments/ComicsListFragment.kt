package com.caiosilva.turbitest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.caiosilva.turbitest.R
import com.caiosilva.turbitest.data.model.ResponseResults
import com.caiosilva.turbitest.databinding.FragmentComicsListBinding
import com.caiosilva.turbitest.util.Constants.COMIC_ID
import com.caiosilva.turbitest.util.ItemClickListener
import com.caiosilva.turbitest.view.adapters.ComicsListAdapter
import com.caiosilva.turbitest.view.viewmodel.ComicsListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicsListFragment : Fragment() {

    private var _binding: FragmentComicsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ComicsListViewModel by viewModel()
    private val adapter = ComicsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComicsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.comicsList.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter.context = requireContext()
        adapter.onClickListener = object : ItemClickListener {
            override fun <T> onItemClick(item: T?) {
                (item as ResponseResults).id?.let { id ->
                    findNavController().navigate(
                        R.id.comicDetailsFragment,
                        Bundle().apply {
                            putInt(COMIC_ID, id)
                        })
                }
            }
        }
        binding.rvCharactersList.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        binding.rvCharactersList.adapter = adapter
    }
}