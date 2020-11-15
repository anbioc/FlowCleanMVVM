package com.multithread.cocoon.mapper

import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.model.localEntity.TopStoryLocalEntity
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

class TopStoryLocalSingleMapper @Inject constructor() :
    Mapper<TopStoryDomainEntity.Result, TopStoryLocalEntity> {

    override fun map(items: TopStoryDomainEntity.Result): TopStoryLocalEntity = TopStoryLocalEntity(
        storyAbstract = items.abstract,
        byline = items.byline,
        createdDate = items.createdDate,
        itemType = items.itemType,
        kicker = items.kicker,
        materialTypeFacet = items.materialTypeFacet,
        imageUrl = items.imageUrl,
        publishedDate = items.publishedDate,
        section = items.section,
        shortUrl = items.shortUrl,
        subsection = items.subsection,
        title = items.title,
        updatedDate = items.updatedDate,
        uri = items.uri,
        url = items.url,
        favorite = items.favorite
    )
}