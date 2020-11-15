package com.multithread.cocoon.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.R
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.extension.show
import com.multithread.cocoon.presentation.topstories.CallbackParam
import com.multithread.cocoon.util.ImageLoader
import kotlinx.android.synthetic.main.item_story.view.*

class TopStoriesViewHolder constructor(
    containerView: View,
    private val callback: (entity: CallbackParam) -> Unit,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView) {

    companion object {
        fun create(
            parent: ViewGroup,
            callback: (entity: CallbackParam) -> Unit,
            imageLoader: ImageLoader
        ) =
            TopStoriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_story, parent, false), callback, imageLoader
            )

    }

    fun bind(story: TopStoryDomainEntity.Result) {
        itemView.setOnClickListener {
            callback(CallbackParam.Click(story))
        }
        itemView.itemStoryLikeContainer.setOnClickListener {
            if (story.favorite){
                callback(CallbackParam.Dislike(story))
            }else {
                callback(CallbackParam.Like(story))
            }
        }

        itemView.itemTopStoryIcon.show(story.favorite)
        itemView.findViewById<TextView>(R.id.itemStoryTitle).text = story.title
        itemView.findViewById<TextView>(R.id.itemStoryDateTime).text = story.updatedDate
        imageLoader.loadImage(
            itemView.findViewById<ImageView>(R.id.itemStoryImage),
            0,
            0,
            story.imageUrl
        )
    }
}