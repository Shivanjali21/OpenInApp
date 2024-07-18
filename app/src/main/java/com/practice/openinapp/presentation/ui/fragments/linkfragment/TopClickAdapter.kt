package com.practice.openinapp.presentation.ui.fragments.linkfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.openinapp.databinding.LayoutRvToplinksBinding
import com.practice.openinapp.data.TopLink

class TopClickAdapter() : RecyclerView.Adapter<TopClickAdapter.TLinkViewHolder>() {

    private val diffUtils = object : DiffUtil.ItemCallback<TopLink>() {
        override fun areItemsTheSame(oldItem: TopLink, newItem: TopLink): Boolean {
          return oldItem.url_id == newItem.url_id
        }

        override fun areContentsTheSame(oldItem: TopLink, newItem: TopLink): Boolean {
          return oldItem.url_id == newItem.url_id
        }
    }

    val asyncDiffUtil = AsyncListDiffer(this, diffUtils)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TLinkViewHolder {
      val view = LayoutRvToplinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return TLinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: TLinkViewHolder, position: Int) {
      val topLinkData = asyncDiffUtil.currentList[position]
      holder.binding.apply {
        mtvTopLinkName.text = topLinkData.title
      }
    }

    override fun getItemCount(): Int {
      return asyncDiffUtil.currentList.size
    }

    inner class TLinkViewHolder(val binding : LayoutRvToplinksBinding) : RecyclerView.ViewHolder(binding.root)
}