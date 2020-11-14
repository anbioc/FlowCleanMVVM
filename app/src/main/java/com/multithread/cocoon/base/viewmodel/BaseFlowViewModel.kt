package com.multithread.cocoon.base.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.multithread.cocoon.base.ResultResponse
import com.multithread.cocoon.base.ViewModelState
import kotlinx.coroutines.*


abstract class BaseFlowViewModel<STATE : ViewModelState> : ViewModel() {

    abstract val initialState: STATE

    protected var _state = MutableLiveData<STATE>()
    val state: LiveData<STATE>
        get() = _state

    protected val jobList = mutableListOf<Job>()

    /**
     * Triggers given action in either of failure or success results
     * or if there is another type of result triggers default action.
     */
    fun <T> ResultResponse<T>.subscribe(
        successAction: (t: ResultResponse<T>) -> STATE,
        failureAction: (t: ResultResponse<T>) -> STATE,
        defaultAction: ((t: ResultResponse<T>) -> STATE)? = null,
    ): Any = when {
        this.isSuccess() -> {
            _state.postValue(successAction(this))
        }
        this.isFailure() -> {
            _state.postValue(failureAction(this))
        }
        else -> {
            _state.postValue(
                defaultAction?.let {
                    it(this)
                }
            )
        }
    }

    /**
     * Starts an action and adds resulted job to the job list.
     */
    fun triggerAction(dispatcher: CoroutineDispatcher, action: suspend () -> Unit) =
        viewModelScope.launch(dispatcher) {
            try{
                action()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }.apply {
            jobList.add(this)
        }

    /**
     * Emits loading state before starting a coroutine for this action then adds resulted job to the job list.
     */
    fun triggerActionStartWithLoading(
        dispatcher: CoroutineDispatcher, action: suspend () -> Unit
    ) = viewModelScope.launch(dispatcher) {
        _state.postValue(loadingState)
        try{
            action()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }.apply {
        jobList.add(this)
    }

    open fun cleanUp() {
        _state = MutableLiveData<STATE>()
    }


    /**
     * Cancels all current coroutine jobs.
     */
    open fun reset() = jobList.forEach {
        if (it.isActive.not())
            it.cancel()
    }.apply {
        jobList.clear()
    }

    fun isCoroutineActive() = jobList.isEmpty().not()

    protected abstract val loadingState: STATE

}


abstract class BaseFlowAndroidViewModel<STATE : ViewModelState>(application: Application) :
    AndroidViewModel(application) {

    protected val _state = MutableLiveData<STATE>()
    val state: LiveData<STATE>
        get() = _state

    abstract val initialState: STATE


    /**
     * Triggers given action in either of failure or success results
     * or if there is another type of result triggers default action.
     */
    fun <T> ResultResponse<T>.subscribe(
        successAction: (t: ResultResponse<T>) -> STATE,
        failureAction: (t: ResultResponse<T>) -> STATE,
        defaultAction: ((t: ResultResponse<T>) -> STATE)? = null
    ) = when {
        this.isSuccess() -> {
            _state.postValue(successAction(this))
        }
        this.isFailure() -> {
            _state.postValue(failureAction(this))
        }
        else -> {
            _state.postValue(
                defaultAction?.let {
                    it(this)
                }
            )
        }
    }


}



