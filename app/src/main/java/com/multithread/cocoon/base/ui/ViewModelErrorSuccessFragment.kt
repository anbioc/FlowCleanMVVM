package com.multithread.cocoon.base.ui


import com.multithread.cocoon.base.BaseEvent
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel


abstract class ViewModelErrorSuccessFragment<STATE : ViewModelState, EVENT : BaseEvent,
        VIEWMODEL : BaseFlowViewModel<STATE, EVENT>> :
    BaseViewModelFragment<STATE, EVENT, VIEWMODEL>() {


//    @Inject
//    lateinit var imageLoader: ImageLoader


    override fun renderState(state: STATE) {
        super.renderState(state)
        showLoadingSpinner(state.baseState.loading)
    }

    abstract fun showLoadingSpinner(loading: Boolean)


}