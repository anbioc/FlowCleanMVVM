package com.multithread.cocoon.domain.model

import com.multithread.cocoon.data.model.dto.TopStoryDTO

data class TopStoryDomainEntity(
    var copyright: String,
    var lastUpdated: String,
    var numResults: Int,
    var results: List<TopStoryDTO.Result>,
    var section: String,
    var status: String
) {
    data class Result(
        var abstract: String,
        var byline: String,
        var createdDate: String,
        var desFacet: List<String>,
        var geoFacet: List<String>,
        var itemType: String,
        var kicker: String,
        var materialTypeFacet: String,
        var multimedia: List<Multimedia>,
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
    ) {
        data class Multimedia(
            var caption: String,
            var copyright: String,
            var format: String,
            var height: Int,
            var subtype: String,
            var type: String,
            var url: String,
            var width: Int
        )
    }
}