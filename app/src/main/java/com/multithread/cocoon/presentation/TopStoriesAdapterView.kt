package com.multithread.cocoon.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.util.ImageLoader

class TopStoriesAdapterView(
    private val callback: (entity: TopStoryDTO.Result) -> Unit,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<TopStoriesViewHolder>() {

    var itemList: List<TopStoryDTO.Result> = emptyList()
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