package com.multithread.cocoon.mapper

import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

class TopStoryDomainMapper @Inject constructor() : Mapper<TopStoryDTO, TopStoryDomainEntity> {
    override fun map(items: TopStoryDTO): TopStoryDomainEntity = TopStoryDomainEntity(
            results = createResult(items.results)
    )

    private fun createResult(results: List<TopStoryDTO.Result>): List<TopStoryDomainEntity.Result> =
            mutableListOf<TopStoryDomainEntity.Result>().apply {
                results.forEach { result ->
                    TopStoryDomainEntity.Result(
                            abstract = result.abstract,
                            byline = result.abstract,
                            createdDate = result.abstract,
                            desFacet = result.desFacet,
                            geoFacet = result.geoFacet,
                            itemType = result.itemType,
                            kicker = result.kicker,
                            materialTypeFacet = result.abstract,
                            imageUrl = createMultimedia(result.multimedia),
                            orgFacet = result.orgFacet,
                            perFacet = result.perFacet,
                            publishedDate = result.publishedDate,
                            section = result.section,
                            shortUrl = result.shortUrl,
                            subsection = result.subsection,
                            title = result.title,
                            updatedDate = result.updatedDate,
                            uri = result.uri,
                            url = result.url
                    )
                }
            }


    private fun createMultimedia(multimediaList: List<TopStoryDTO.Result.Multimedia>) =
            multimediaList[0].url

}