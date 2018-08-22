package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView

open interface RegisterView : BaseView {

    fun onRegisterResult(result : String)
}