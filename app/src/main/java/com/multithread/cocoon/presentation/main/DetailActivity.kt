package com.multithread.cocoon.presentation.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.multithread.cocoon.AppConstants
import com.multithread.cocoon.R
import com.multithread.cocoon.base.ui.FullScreenActivity
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.util.ImageLoader
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : FullScreenActivity<MainState, MainEvent, MainViewModel>() {
    private var story: TopStoryDomainEntity.Result? = null
    override val contentResourceId: Int = com.multithread.cocoon.R.layout.activity_detail


    @Inject
    lateinit var imageLoader: ImageLoader

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initView() {
        super.initView()
        getInfo()

        story?.apply {
            detailTitleTextView.text = title
            detailMessageTextView.text = abstract
            detailLinkTextView.text = getString(R.string.story_link)
            setLinkListener()
            imageLoader.loadImage(
                detailImageView, 0, 0, imageUrl)
        }
    }

    private fun setLinkListener() {
        detailLinkTextView.setOnClickListener {
            Uri.parse(story!!.url).apply {
                startActivity(Intent(Intent.ACTION_VIEW, this))
            }
        }
    }

    private fun getInfo() {
        intent?.let {
            story =
                it.getParcelableExtra<TopStoryDomainEntity.Result>(AppConstants.NavigationKey.TOP_STORY_KEY)
        }
    }

    companion object {
        fun newInstance(context: Context,result: TopStoryDomainEntity.Result ) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(AppConstants.NavigationKey.TOP_STORY_KEY, result)
            }
    }
}