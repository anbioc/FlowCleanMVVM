package com.multithread.cocoon.presentation.topstories

import com.multithread.cocoon.base.BaseEvent
import com.multithread.cocoon.base.BaseState
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.domain.model.TopStoryDomainEntity

sealed class TopStoriesEvent : BaseEvent {
    object GetTopStories : TopStoriesEvent()
}

data class TopStoriesState(
    val data: TopStoriesState.Data = TopStoriesState.Data.NoData,
    override var baseState: BaseState = BaseState()
) : ViewModelState() {
    sealed class Data {
        data class TopStories(val story: TopStoryDomainEntity) : Data()
        object NoData : Data()
    }
}
