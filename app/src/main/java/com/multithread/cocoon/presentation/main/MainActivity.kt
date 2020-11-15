package com.multithread.cocoon.presentation.main

import com.multithread.cocoon.R
import com.multithread.cocoon.base.ui.FullScreenActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FullScreenActivity<MainState, MainEvent, MainViewModel>() {

    override val contentResourceId: Int
        get() = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java


    override fun initView() {
        super.initView()
        StoriesFragmentViewPagerAdapter(supportFragmentManager).apply {
            mainViewPager.adapter = this
            mainTabLayout.setupWithViewPager(mainViewPager)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 0){
            supportFragmentManager.popBackStack()
        }else {
            super.onBackPressed()
        }
    }
}