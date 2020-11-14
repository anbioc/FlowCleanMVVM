package com.multithread.cocoon.data.model.dto


import com.google.gson.annotations.SerializedName

data class TopStoryDTO(
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("last_updated")
    var lastUpdated: String,
    @SerializedName("num_results")
    var numResults: Int,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("section")
    var section: String,
    @SerializedName("status")
    var status: String
) {
    data class Result(
        @SerializedName("abstract")
        var `abstract`: String,
        @SerializedName("byline")
        var byline: String,
        @SerializedName("created_date")
        var createdDate: String,
        @SerializedName("des_facet")
        var desFacet: List<String>,
        @SerializedName("geo_facet")
        var geoFacet: List<String>,
        @SerializedName("item_type")
        var itemType: String,
        @SerializedName("kicker")
        var kicker: String,
        @SerializedName("material_type_facet")
        var materialTypeFacet: String,
        @SerializedName("multimedia")
        var multimedia: List<Multimedia>,
        @SerializedName("org_facet")
        var orgFacet: List<String>,
        @SerializedName("per_facet")
        var perFacet: List<String>,
        @SerializedName("published_date")
        var publishedDate: String,
        @SerializedName("section")
        var section: String,
        @SerializedName("short_url")
        var shortUrl: String,
        @SerializedName("subsection")
        var subsection: String,
        @SerializedName("title")
        var title: String,
        @SerializedName("updated_date")
        var updatedDate: String,
        @SerializedName("uri")
        var uri: String,
        @SerializedName("url")
        var url: String
    ) {
        data class Multimedia(
            @SerializedName("caption")
            var caption: String,
            @SerializedName("copyright")
            var copyright: String,
            @SerializedName("format")
            var format: String,
            @SerializedName("height")
            var height: Int,
            @SerializedName("subtype")
            var subtype: String,
            @SerializedName("type")
            var type: String,
            @SerializedName("url")
            var url: String,
            @SerializedName("width")
            var width: Int
        )
    }
}