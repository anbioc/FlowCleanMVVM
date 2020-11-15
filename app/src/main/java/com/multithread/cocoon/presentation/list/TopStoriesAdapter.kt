package com.multithread.cocoon.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.presentation.topstories.CallbackParam
import com.multithread.cocoon.util.ImageLoader

class TopStoriesAdapter(
    private val callback: (entity: CallbackParam) -> Unit,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<TopStoriesViewHolder>() {

    var itemList: List<TopStoryDomainEntity.Result> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder =
        TopStoriesViewHolder.create(parent, callback, imageLoader)

    override fun onBindViewHolder(holder: TopStoriesViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int  = itemList.size

}