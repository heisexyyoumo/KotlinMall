package com.kotlin.user.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.ForgetPwdView
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.presenter.view.ResetPwdView
import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {
        /**
         * 业务逻辑
         */

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        userService.resetPwd(mobile, pwd)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mView.onResetPwdResult("重置密码成功")
                    }
                }, lifecycleProvider)

    }
}