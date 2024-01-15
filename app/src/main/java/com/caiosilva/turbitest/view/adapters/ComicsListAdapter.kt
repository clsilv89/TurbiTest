package com.caiosilva.turbitest.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.caiosilva.turbitest.R
import com.caiosilva.turbitest.data.model.ResponseResults
import com.caiosilva.turbitest.databinding.ItemComicBinding
import com.caiosilva.turbitest.util.ItemClickListener
import com.caiosilva.turbitest.util.view.loadImageWithUrl

class ComicsListAdapter :
    PagingDataAdapter<ResponseResults, ComicsListAdapter.CharacterListViewHolder>(DiffCallback()) {

    lateinit var context: Context
    lateinit var onClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        return CharacterListViewHolder(
            ItemComicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        with(holder) {
            bind(getItem(position))
        }
    }

    inner class CharacterListViewHolder(private val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseResults?) = binding.run {
            tvIssueTitle.text = context.getString(R.string.title_text_view, data?.title)
            tvIssueDescription.text = if (data?.description.isNullOrBlank())
                context.getString(R.string.no_description)
            else
                context.getString(
                    R.string.description_text_view,
                    data?.description
                )
            tvIssueId.text = context.getString(R.string.issue_id_text_view, data?.id.toString())
            tvIssuePageCount.text =
                context.getString(R.string.page_count_text_view, data?.pageCount.toString())
            ivIssueCover.loadImageWithUrl(data?.thumbnail?.path + "." + data?.thumbnail?.extension)
            root.setOnClickListener {
                onClickListener.onItemClick(data)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ResponseResults>() {
        override fun areItemsTheSame(oldItem: ResponseResults, newItem: ResponseResults): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseResults,
            newItem: ResponseResults
        ): Boolean {
            return oldItem == newItem
        }
    }
}