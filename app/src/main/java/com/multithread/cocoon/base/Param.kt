package com.multithread.cocoon.base

import com.multithread.cocoon.domain.model.TopStoryDomainEntity


abstract class Param

class AnyParam : Param()


sealed class TopStoriesParam : Param() {
    object GetTopStories : TopStoriesParam()
    data class AddToFavorites(val data: TopStoryDomainEntity.Result) : TopStoriesParam()
    data class RemoveFromFavorites(val data: TopStoryDomainEntity.Result) : TopStoriesParam()
}
