package com.multithread.cocoon.mapper

import com.multithread.cocoon.base.Mapper
import com.multithread.cocoon.data.model.dto.TopStoryDTO
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import javax.inject.Inject

class TopStoryDomainMapper @Inject constructor() : Mapper<TopStoryDTO, TopStoryDomainEntity> {
    override fun map(items: TopStoryDTO): TopStoryDomainEntity = TopStoryDomainEntity(
        copyright = items.copyright,
        lastUpdated = items.lastUpdated,
        numResults = items.numResults,
        results = createResult(items.results),
        section = items.section,
        status = items.status
    )

    private fun createResult(results: List<TopStoryDTO.Result>): List<TopStoryDTO.Result> =
        mutableListOf<TopStoryDTO.Result>().apply {
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
                    multimedia = createMultimedia(result.multimedia),
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

    private fun createMultimedia(multimediaList: List<TopStoryDTO.Result.Multimedia>):
            List<TopStoryDomainEntity.Result.Multimedia> =
        mutableListOf<TopStoryDomainEntity.Result.Multimedia>().apply {
            multimediaList.forEach { multiMedia ->
                TopStoryDomainEntity.Result.Multimedia(
                    caption = multiMedia.caption,
                    copyright = multiMedia.copyright,
                    format = multiMedia.format,
                    height = multiMedia.height,
                    subtype = multiMedia.subtype,
                    type = multiMedia.type,
                    url = multiMedia.url,
                    width = multiMedia.width
                )
            }
        }

}