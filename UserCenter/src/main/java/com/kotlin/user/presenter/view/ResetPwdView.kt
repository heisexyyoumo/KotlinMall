package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

open interface ResetPwdView : BaseView {

    fun onResetPwdResult(result : String)
}