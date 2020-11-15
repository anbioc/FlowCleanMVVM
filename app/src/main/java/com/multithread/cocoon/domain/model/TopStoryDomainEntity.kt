package com.multithread.cocoon.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.multithread.cocoon.base.BaseDataModel

data class TopStoryDomainEntity(
    val results: List<Result> = emptyList()
) : BaseDataModel, Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Result) ?: emptyList()) {
    }

    data class Result(
        var abstract: String,
        var byline: String,
        var createdDate: String,
        var itemType: String,
        var kicker: String,
        var materialTypeFacet: String,
        var imageUrl: String,
        var publishedDate: String,
        var section: String,
        var shortUrl: String,
        var subsection: String,
        var title: String,
        var updatedDate: String,
        var uri: String,
        var url: String,
        var favorite: Boolean = false
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt() == 1
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(abstract)
            parcel.writeString(byline)
            parcel.writeString(createdDate)
            parcel.writeString(itemType)
            parcel.writeString(kicker)
            parcel.writeString(materialTypeFacet)
            parcel.writeString(imageUrl)
            parcel.writeString(publishedDate)
            parcel.writeString(section)
            parcel.writeString(shortUrl)
            parcel.writeString(subsection)
            parcel.writeString(title)
            parcel.writeString(updatedDate)
            parcel.writeString(uri)
            parcel.writeString(url)
            parcel.writeInt(if (favorite) 1 else 0)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Result> {
            override fun createFromParcel(parcel: Parcel): Result {
                return Result(parcel)
            }

            override fun newArray(size: Int): Array<Result?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopStoryDomainEntity> {
        override fun createFromParcel(parcel: Parcel): TopStoryDomainEntity {
            return TopStoryDomainEntity(parcel)
        }

        override fun newArray(size: Int): Array<TopStoryDomainEntity?> {
            return arrayOfNulls(size)
        }
    }
}