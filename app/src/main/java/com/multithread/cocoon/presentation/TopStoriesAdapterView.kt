package com.multithread.cocoon.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.multithread.cocoon.domain.model.TopStoryDomainEntity

class TopStoriesAdapterView(
    private val callback: (entity: TopStoryDomainEntity) -> Unit
) : RecyclerView.Adapter<TopStoriesViewHolder>() {

    var itemList: List<TopStoryDomainEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopStoriesViewHolder =
        TopStoriesViewHolder.create(parent, callback)

    override fun onBindViewHolder(holder: TopStoriesViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int  = itemList.size

}