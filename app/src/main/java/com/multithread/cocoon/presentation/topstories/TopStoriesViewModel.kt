package com.multithread.cocoon.presentation.topstories

import com.multithread.cocoon.base.*
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import com.multithread.cocoon.di.scope.MainDispatcher
import com.multithread.cocoon.domain.usecase.TopStoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TopStoriesViewModel @Inject constructor(
    private val topStoriesUseCase: TopStoriesUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : BaseFlowViewModel<TopStoriesState, TopStoriesEvent>() {

    override val initialState: TopStoriesState = TopStoriesState()
    override val loadingState: TopStoriesState = initialState.loading()

    @FlowPreview
    override fun handleEvent(event: TopStoriesEvent) {
        when (event) {
            is TopStoriesEvent.GetTopStories -> {
                getTopStories()
            }
            else -> {
                // do nothing
            }
        }
    }

    @FlowPreview
    private fun getTopStories() = triggerActionStartWithLoading(dispatcher) {
        topStoriesUseCase.execute(AnyParam()).collect { result ->
            result.subscribe(
                successAction = {
                    initialState.copy(
                        data = TopStoriesState.Data.TopStories(it.extractData()),
                        baseState = initialState.baseState.noErrorNoLoading()
                    )
                },
                failureAction = {
                    initialState.copy(
                        data = TopStoriesState.Data.NoData,
                        baseState = initialState.baseState.onErrorNoLoading(it.extractError())
                    )
                }
            )
        }
    }
}

