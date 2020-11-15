package com.multithread.cocoon.base.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.multithread.cocoon.base.BaseEvent
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel

abstract class FullScreenActivity<STATE : ViewModelState, EVENT : BaseEvent, VIEWMODEL : BaseFlowViewModel<STATE, EVENT>> :
    BaseActivity<STATE, EVENT, VIEWMODEL>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.apply {
            hide()
        }
        super.onCreate(savedInstanceState)
    }


}