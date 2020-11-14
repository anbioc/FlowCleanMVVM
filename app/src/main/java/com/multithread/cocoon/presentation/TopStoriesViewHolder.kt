package com.multithread.cocoon.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.R
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.util.ImageLoader

class TopStoriesViewHolder constructor(
    private val containerView: View,
    private val callback: (entity: TopStoryDTO.Result) -> Unit,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {

    companion object {
        fun create(parent: ViewGroup,
                   callback: (entity: TopStoryDTO.Result) -> Unit,
                   imageLoader: ImageLoader) =
            TopStoriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_story, parent, false), callback, imageLoader)

    }

    fun bind(story: TopStoryDTO.Result) {
        itemStoryTitle.text = story.title
        itemStoryDateTime.text = story.updatedDate
        imageLoader.loadImage(
            itemStoryImage,
            0,
            0,
            story.multimedia[0].url
        )
    }
}