package com.multithread.cocoon.presentation.topstories

import com.multithread.cocoon.base.*
import com.multithread.cocoon.base.domain.GeneralUseCase
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import com.multithread.cocoon.di.scope.MainDispatcher
import com.multithread.cocoon.domain.model.TopStoryDomainEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TopStoriesViewModel @Inject constructor(
    private val topStoriesUseCase: GeneralUseCase<TopStoriesParam, TopStoryDomainEntity>,
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
            is TopStoriesEvent.AddToFavorite -> {
                changeFavoriteStatus(
                    event.data,
                    TopStoriesParam.AddToFavorites(event.data)
                )
            }
            is TopStoriesEvent.RemoveFromFavorite -> {
                changeFavoriteStatus(
                    event.data,
                    TopStoriesParam.RemoveFromFavorites(event.data)
                )
            }
            else -> {
                // do nothing
            }
        }
    }

    @FlowPreview
    private fun changeFavoriteStatus(data: TopStoryDomainEntity.Result, param: TopStoriesParam) =
        triggerActionStartWithLoading(dispatcher) {
            topStoriesUseCase.execute(param).collect { result ->
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

    @FlowPreview
    private fun getTopStories() = triggerActionStartWithLoading(dispatcher) {
        topStoriesUseCase.execute(TopStoriesParam.GetTopStories).collect { result ->
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

