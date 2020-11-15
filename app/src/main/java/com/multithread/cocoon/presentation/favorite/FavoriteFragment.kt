package com.multithread.cocoon.presentation.favorite

import com.multithread.cocoon.base.ui.ViewModelErrorSuccessFragment
import com.multithread.cocoon.extension.show
import com.multithread.cocoon.presentation.list.TopStoriesAdapter
import com.multithread.cocoon.presentation.main.DetailActivity
import com.multithread.cocoon.presentation.topstories.CallbackParam
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.coroutines.FlowPreview

class FavoriteFragment :
    ViewModelErrorSuccessFragment<FavoriteTopStoriesState, FavoriteTopStoriesEvent, FavoriteViewModel>() {

    @FlowPreview
    private val storyCallback: (item: CallbackParam) -> Unit = {
        when (it){
            is CallbackParam.Click -> {
                startActivity(DetailActivity.newInstance(requireContext(), it.data))
            }
            else -> {
                // do nothing
            }
        }
    }

    private val storiesAdapter by lazy {
        TopStoriesAdapter(storyCallback, imageLoader)
    }

    override fun showLoadingSpinner(loading: Boolean) {

    }

    override fun getViewModelClass(): Class<FavoriteViewModel> = FavoriteViewModel::class.java

    override val contentResourceId: Int = com.multithread.cocoon.R.layout.fragment_favorite

    override fun initView() {
        super.initView()
        favoriteList.apply {
            adapter = storiesAdapter
            setHasFixedSize(true)
        }
    }

    @FlowPreview
    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(FavoriteTopStoriesEvent.GetFavoriteTopStories)
    }

    override fun renderState(state: FavoriteTopStoriesState) {
        super.renderState(state)
        when (state.data) {
            is FavoriteTopStoriesState.Data.TopStories -> {
                onDataReceived(state.data)
            }
            else -> {
                onNoData()
            }
        }
    }

    private fun onDataReceived(data: FavoriteTopStoriesState.Data.TopStories) =
        if (data.story.results.isEmpty().not()) {
            favoriteList.show(true)
            storiesAdapter.itemList = data.story.results
            favoriteEmptyTextView.show(false)
        } else {
            favoriteList.show(false)
            favoriteEmptyTextView.show(true)
        }

    private fun onNoData() {
        favoriteList.show(false)
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }

}