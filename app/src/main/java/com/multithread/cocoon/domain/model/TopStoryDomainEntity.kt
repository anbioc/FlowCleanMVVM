package com.multithread.cocoon.domain.model

import com.multithread.cocoon.base.BaseDataModel

data class TopStoryDomainEntity(
        val results: List<Result> = emptyList()
) : BaseDataModel {
    data class Result(
            var abstract: String,
            var byline: String,
            var createdDate: String,
            var desFacet: List<String>,
            var geoFacet: List<String>,
            var itemType: String,
            var kicker: String,
            var materialTypeFacet: String,
            var imageUrl: String,
            var orgFacet: List<String>,
            var perFacet: List<String>,
            var publishedDate: String,
            var section: String,
            var shortUrl: String,
            var subsection: String,
            var title: String,
            var updatedDate: String,
            var uri: String,
            var url: String
    )
}