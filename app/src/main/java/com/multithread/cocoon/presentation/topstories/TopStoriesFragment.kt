package com.multithread.cocoon.presentation.topstories

import com.multithread.cocoon.base.ui.ViewModelErrorSuccessFragment

class TopStoriesFragment : ViewModelErrorSuccessFragment<TopStoriesState, TopStoriesViewModel>(){

    override fun showLoadingSpinner(loading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getViewModelClass(): Class<TopStoriesViewModel> = TopStoriesViewModel::class.java

    override val contentResourceId: Int = R.layout.fragment_top_stories

    override fun initView() {
        super.initView()

    }

}