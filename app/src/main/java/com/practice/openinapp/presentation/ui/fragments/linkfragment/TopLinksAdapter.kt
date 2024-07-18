package com.practice.openinapp.presentation.ui.fragments.linkfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.openinapp.databinding.LayoutRvToplinksBinding
import com.practice.openinapp.data.TopLink
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TopLinksAdapter() : RecyclerView.Adapter<TopLinksAdapter.TLinkViewHolder>() {

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
        mtvTopLinkCount.text = topLinkData.total_clicks.toString()

        // Format and set the date
        val formattedDate = formatDate(topLinkData.created_at)
        mtvTopLinkDate.text = formattedDate

      mtvTopLink.text = topLinkData.web_link
      }
    }

    private fun formatDate(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val date: Date? = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) } ?: ""
        } catch (e: Exception) { "" }
    }

    override fun getItemCount(): Int {
      return asyncDiffUtil.currentList.size
    }

    inner class TLinkViewHolder(val binding : LayoutRvToplinksBinding) : RecyclerView.ViewHolder(binding.root)
}