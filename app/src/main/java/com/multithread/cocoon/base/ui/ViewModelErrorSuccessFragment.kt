package com.multithread.cocoon.base.ui


import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel


abstract class ViewModelErrorSuccessFragment<STATE : ViewModelState,
        VIEWMODEL : BaseFlowViewModel<STATE>> : BaseViewModelFragment<STATE, VIEWMODEL>(){


//    @Inject
//    lateinit var imageLoader: ImageLoader


    override fun renderState(state: STATE) {
        super.renderState(state)
        showLoadingSpinner(state.baseState.loading)
    }

    abstract fun showLoadingSpinner(loading: Boolean)


}