package com.multithread.cocoon.presentation.favorite

import com.multithread.cocoon.base.BaseEvent
import com.multithread.cocoon.base.BaseState
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.domain.model.TopStoryDomainEntity


sealed class FavoriteTopStoriesEvent : BaseEvent {
    object GetFavoriteTopStories : FavoriteTopStoriesEvent()
}

data class FavoriteTopStoriesState(
        val data: Data = Data.NoData,
        override var baseState: BaseState = BaseState()
) : ViewModelState() {
    sealed class Data {
        data class TopStories(val story: TopStoryDomainEntity) : Data()
        object NoData : Data()
    }
}
