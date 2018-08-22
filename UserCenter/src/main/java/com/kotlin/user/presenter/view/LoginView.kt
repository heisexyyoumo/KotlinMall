package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

open interface LoginView : BaseView {

    fun onLoginResult(result : UserInfo)
}