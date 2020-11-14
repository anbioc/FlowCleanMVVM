package com.multithread.cocoon.presentation.topstories

import com.multithread.cocoon.base.*
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import com.multithread.cocoon.di.MainDispatcher
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import com.multithread.cocoon.domain.usecase.TopStoriesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TopStoriesViewModel @Inject constructor(
    private val topStoriesUseCase: TopStoriesUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) :
    BaseFlowViewModel<TopStoriesState, TopStoriesEvent>() {
    override val initialState: TopStoriesState
        get() = TopStoriesState()
    override val loadingState: TopStoriesState
        get() = loadingState.loading()

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

sealed class TopStoriesEvent : BaseEvent {
    object GetTopStories : TopStoriesEvent()
}

data class TopStoriesState(
    val data: TopStoriesState.Data,
    override var baseState: BaseState = BaseState()
) : ViewModelState() {
    sealed class Data {
        data class TopStories(val story: TopStoryDomainEntity) : Data
        object NoData : Data()
    }
}
