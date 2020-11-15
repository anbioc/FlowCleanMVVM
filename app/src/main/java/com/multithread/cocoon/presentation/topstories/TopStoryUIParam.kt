package com.multithread.cocoon.presentation.topstories

import com.multithread.cocoon.base.BaseEvent
import com.multithread.cocoon.base.BaseState
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.domain.model.TopStoryDomainEntity


sealed class CallbackParam{
    data class Dislike(val data: TopStoryDomainEntity.Result): CallbackParam()
    data class Like(val data: TopStoryDomainEntity.Result): CallbackParam()
    data class Click(val data: TopStoryDomainEntity.Result): CallbackParam()

}

sealed class TopStoriesEvent : BaseEvent {
    object GetTopStories : TopStoriesEvent()
    object GetFavoriteTopStories : TopStoriesEvent()
    data class AddToFavorite(val data: TopStoryDomainEntity.Result): TopStoriesEvent()
    data class RemoveFromFavorite(val data: TopStoryDomainEntity.Result): TopStoriesEvent()
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
