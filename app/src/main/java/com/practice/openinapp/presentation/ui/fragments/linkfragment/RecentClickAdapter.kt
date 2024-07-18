package com.practice.openinapp.presentation.ui.fragments.linkfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.openinapp.databinding.LayoutRvRecentlinksBinding
import com.practice.openinapp.data.RecentLink

class RecentClickAdapter() : RecyclerView.Adapter<RecentClickAdapter.TLinkViewHolder>() {

    private val diffUtils = object : DiffUtil.ItemCallback<RecentLink>() {
        override fun areItemsTheSame(oldItem: RecentLink, newItem: RecentLink): Boolean {
          return oldItem.url_id == newItem.url_id
        }

        override fun areContentsTheSame(oldItem: RecentLink, newItem: RecentLink): Boolean {
          return oldItem.url_id == newItem.url_id
        }
    }

    val asyncDiffUtil = AsyncListDiffer(this, diffUtils)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TLinkViewHolder {
      val view = LayoutRvRecentlinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return TLinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: TLinkViewHolder, position: Int) {
      val recentLinkData = asyncDiffUtil.currentList[position]
      holder.binding.apply {
        mtvRecentLinkName.text = recentLinkData.title
      }
    }

    override fun getItemCount(): Int {
      return asyncDiffUtil.currentList.size
    }

    inner class TLinkViewHolder(val binding : LayoutRvRecentlinksBinding) : RecyclerView.ViewHolder(binding.root)
}