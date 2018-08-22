package com.kotlin.order.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

open interface OrderDetailView : BaseView {


    fun onGetOrderByIdResult(result: Order)

}