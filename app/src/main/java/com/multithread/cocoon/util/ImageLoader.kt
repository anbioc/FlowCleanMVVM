package com.multithread.cocoon.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject
import javax.inject.Singleton


interface ImageLoader {

    fun loadImage(
        view: ImageView,
        placeHolderId: Int,
        errorPlaceHolderId: Int,
        imageUrl: String
    )

}

@Singleton
class GliderImageLoaderImpl @Inject constructor(private val context: Context) : ImageLoader {

    override fun loadImage(
        view: ImageView,
        placeHolderId: Int,
        errorPlaceHolderId: Int,
        imageUrl: String
    ) {
        try {
            Glide.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(getBgRequestOption()).apply {
                    if (placeHolderId > 0)
                        this.placeholder(placeHolderId)
                    if (errorPlaceHolderId > 0)
                        this.error(errorPlaceHolderId)
                }.into(view)
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    companion object {
        fun getBgRequestOption(width: Int = 0, height: Int = 0): RequestOptions =
            RequestOptions().apply {
                if (width > 0 && height > 0)
                    override(width, height)
                diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            }
    }
}
