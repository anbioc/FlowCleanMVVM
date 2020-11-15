package com.multithread.cocoon.presentation.main

import com.multithread.cocoon.base.BaseEvent
import com.multithread.cocoon.base.BaseState
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.base.loading
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseFlowViewModel<MainState, MainEvent>() {
    override val initialState: MainState
        get() = MainState()

    override fun handleEvent(event: MainEvent) {
        // do nothing
    }

    override val loadingState: MainState
        get() = initialState.loading()

}

sealed class MainEvent : BaseEvent {
}

data class MainState(
        override var baseState: BaseState = BaseState()
) : ViewModelState()
