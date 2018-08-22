package com.kotlin.pay.presenter.view

import com.kotlin.base.presenter.view.BaseView

open interface PayView : BaseView {


    fun onGetSignResult(result: String)
    fun onPayOrderResult(result: Boolean)

}