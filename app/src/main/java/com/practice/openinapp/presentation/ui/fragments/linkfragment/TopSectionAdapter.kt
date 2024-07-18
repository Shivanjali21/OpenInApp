package com.practice.openinapp.presentation.ui.fragments.linkfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.openinapp.databinding.LayoutRvTopsectionBinding
import com.practice.openinapp.data.TopSectionData
import javax.inject.Inject

class TopSectionAdapter @Inject constructor(private var context: Context) : RecyclerView.Adapter<TopSectionAdapter.TLinkViewHolder>() {

    private val diffUtils = object : DiffUtil.ItemCallback<TopSectionData>() {
        override fun areItemsTheSame(oldItem: TopSectionData, newItem: TopSectionData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TopSectionData, newItem: TopSectionData): Boolean {
            return oldItem == newItem
        }
    }

    val asyncDiffUtil = AsyncListDiffer(this, diffUtils)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TLinkViewHolder {
        val view = LayoutRvTopsectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TLinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: TLinkViewHolder, position: Int) {
        val topSectionData = asyncDiffUtil.currentList[position]
        holder.binding.apply {

            //"Today's Clicks"
            ivTopSection.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    topSectionData.drawable
                )
            )
            mtvTopSName.text = topSectionData.titleMain
            mtvTopSEvents.text = topSectionData.titleSub

            //  "Top Location"
            ivTopSection.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    topSectionData.drawable
                )
            )
            mtvTopSName.text = topSectionData.titleMain
            mtvTopSEvents.text = topSectionData.titleSub

            //"Top Source"
            ivTopSection.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    topSectionData.drawable
                )
            )
            mtvTopSName.text = topSectionData.titleMain
            mtvTopSEvents.text = topSectionData.titleSub
        }
    }

    override fun getItemCount(): Int {
        return asyncDiffUtil.currentList.size
    }

    inner class TLinkViewHolder(val binding: LayoutRvTopsectionBinding) :
        RecyclerView.ViewHolder(binding.root)
}