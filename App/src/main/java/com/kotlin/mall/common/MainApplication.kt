package com.kotlin.mall.common

import cn.jpush.android.api.JPushInterface
import com.kotlin.base.common.BaseApplication

class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)

    }

}