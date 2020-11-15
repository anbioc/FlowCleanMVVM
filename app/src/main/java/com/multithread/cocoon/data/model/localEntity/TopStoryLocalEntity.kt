package com.multithread.cocoon.data.model.localEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.multithread.cocoon.base.BaseDataModel

@Entity(tableName = "story", primaryKeys = ["title"])
data class TopStoryLocalEntity(
        @ColumnInfo(name = "abstract")
        val storyAbstract: String,
        val byline: String,
        val createdDate: String,
        val itemType: String,
        val kicker: String,
        val materialTypeFacet: String,
        val imageUrl: String,
        val publishedDate: String,
        val section: String,
        val shortUrl: String,
        val subsection: String,
        val title: String,
        val updatedDate: String,
        val uri: String,
        val url: String,
        val favorite: Boolean
): BaseDataModel {

}