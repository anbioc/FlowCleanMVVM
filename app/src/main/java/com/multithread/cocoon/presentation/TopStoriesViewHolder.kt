package com.multithread.cocoon.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.R
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.domain.model.TopStoryDomainEntity

class TopStoriesViewHolder constructor(
    private val containerView: View,
    private val callback: (entity: TopStoryDTO.Result) -> Unit,
) : RecyclerView.ViewHolder(containerView) {

    companion object {
        fun create(parent: ViewGroup, callback: (entity: TopStoryDTO.Result) -> Unit) =
            TopStoriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_story, parent, false), callback)

    }

    fun bind(story: TopStoryDTO.Result) {
        itemStoryTitle.text = story.title
        itemStoryDateTime.text = story.updatedDate

    }
}