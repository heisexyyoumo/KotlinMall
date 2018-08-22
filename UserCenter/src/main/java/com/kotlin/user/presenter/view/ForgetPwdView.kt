package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

open interface ForgetPwdView : BaseView {

    fun onForgetPwdResult(result : String)
}