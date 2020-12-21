package com.multithread.cocoon.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.multithread.cocoon.base.BaseEvent

import com.multithread.cocoon.base.NetworkState
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import com.multithread.cocoon.di.scope.DefaultDispatcher
import com.multithread.cocoon.error.ErrorEntity
import com.multithread.cocoon.extension.observeLiveData
import kotlinx.coroutines.*
import javax.inject.Inject

abstract class BaseViewModelFragment<STATE : ViewModelState, EVENT: BaseEvent,
        VIEWMODEL : BaseFlowViewModel<STATE, EVENT>> : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    @Inject
//    lateinit var networkState: NetworkState

    @DefaultDispatcher
    @Inject
    lateinit var dispatcher: CoroutineDispatcher

    val viewModel: VIEWMODEL by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(getViewModelClass())
    }

    private val jobList = mutableListOf<Job>()
    protected abstract fun getViewModelClass(): Class<VIEWMODEL>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(contentResourceId, container, false)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        jobList.forEach { it.cancel() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewListeners()
        observeState()
    }

    protected open fun initView() {

    }

    protected open fun initViewListeners() {

    }

    /**
     * Observes state live-data and pass the result to the renderState.
     */
    private fun observeState() = viewLifecycleOwner.observeLiveData(viewModel.state) {
        renderState(it)
    }


    /**
     * Process the state, if any error happens, calls checkForError function.
     * If the state contains more than error and loading states,
     * function should be overridden and the logic should be provided by the developer.
     * @param state that will be returned by view model.
     */
    protected open fun renderState(state: STATE) {
        if (state.baseState.error.isError()) {
            checkForError(state.baseState.error)
        }
        // Call super after overriding this method.
    }

    /**
     * Processes error state of the fragment.
     */
    protected open fun checkForError(error: ErrorEntity) {

    }

    protected fun triggerDelayedAction(delayTime: Long, action: () -> Unit) {
        CoroutineScope(dispatcher).launch {
            delay(delayTime)
            action()
        }.apply {
            jobList.add(this)
        }
    }
}