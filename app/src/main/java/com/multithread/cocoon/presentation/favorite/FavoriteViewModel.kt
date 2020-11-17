package com.multithread.cocoon.presentation.favorite

import com.multithread.cocoon.base.*
import com.multithread.cocoon.base.domain.GeneralUseCase
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import com.multithread.cocoon.di.scope.MainDispatcher
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val topStoriesUseCase: GeneralUseCase<TopStoriesParam, TopStoryDomainEntity>,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : BaseFlowViewModel<FavoriteTopStoriesState, FavoriteTopStoriesEvent>() {

    override val initialState: FavoriteTopStoriesState
        get() = FavoriteTopStoriesState()

    override val loadingState: FavoriteTopStoriesState
        get() = initialState.loading()

    @FlowPreview
    override fun handleEvent(event: FavoriteTopStoriesEvent) {
        when (event) {
            is FavoriteTopStoriesEvent.GetFavoriteTopStories -> {
                getTopStories(TopStoriesParam.GetFavoriteTopStory)
            }
            else -> {
                // do nothing
            }
        }
    }

    @FlowPreview
    private fun getTopStories(param: TopStoriesParam) = triggerActionStartWithLoading(dispatcher) {
        topStoriesUseCase.execute(param).collect { result ->
            result.subscribe(
                successAction = {
                    initialState.copy(
                        data = FavoriteTopStoriesState.Data.TopStories(it.extractData()),
                        baseState = initialState.baseState.noErrorNoLoading()
                    )
                },
                failureAction = {
                    initialState.copy(
                        data = FavoriteTopStoriesState.Data.NoData,
                        baseState = initialState.baseState.onErrorNoLoading(it.extractError())
                    )
                }
            )
        }
    }

}

