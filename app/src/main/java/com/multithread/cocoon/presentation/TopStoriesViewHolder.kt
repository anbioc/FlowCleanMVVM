package com.multithread.cocoon.presentation

import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.R
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.util.ImageLoader

class TopStoriesViewHolder constructor(
    containerView: View,
    private val callback: (entity: TopStoryDTO.Result) -> Unit,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {

    companion object {
        fun create(
            parent: ViewGroup,
            callback: (entity: TopStoryDTO.Result) -> Unit,
            imageLoader: ImageLoader
        ) =
            TopStoriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_story, parent, false), callback, imageLoader
            )

    }

    fun bind(story: TopStoryDTO.Result) {
        itemView.findViewById<TextView>(R.id.itemStoryTitle).text = story.title
        itemView.findViewById<TextView>(R.id.itemStoryDateTime).text = story.updatedDate
        imageLoader.loadImage(
            itemView.findViewById<ImageView>(R.id.itemStoryImage),
            0,
            0,
            story.multimedia[0].url
        )
    }
}