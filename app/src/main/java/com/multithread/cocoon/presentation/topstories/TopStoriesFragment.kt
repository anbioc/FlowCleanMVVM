package com.multithread.cocoon.presentation.topstories

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.multithread.cocoon.base.ui.ViewModelErrorSuccessFragment
import kotlinx.coroutines.FlowPreview

class TopStoriesFragment :
    ViewModelErrorSuccessFragment<TopStoriesState, TopStoriesEvent, TopStoriesViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    override fun showLoadingSpinner(loading: Boolean) {
        topStoriesProgressBar.show(loading)
    }

    override fun getViewModelClass(): Class<TopStoriesViewModel> = TopStoriesViewModel::class.java

    override val contentResourceId: Int = com.multithread.cocoon.R.layout.fragment_top_stories

    @FlowPreview
    override fun initView() {
        super.initView()
        viewModel.handleEvent(TopStoriesEvent.GetTopStories)
    }
    @FlowPreview
    override fun onRefresh() {
        viewModel.handleEvent(TopStoriesEvent.GetTopStories)
        topStoriesSwipeRefresh.setRefresh(false)
    }

}