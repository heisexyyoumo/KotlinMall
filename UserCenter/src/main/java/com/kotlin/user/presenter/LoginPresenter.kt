package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        /**
         * 业务逻辑
         */

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.login(mobile, pwd, pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)

    }
}