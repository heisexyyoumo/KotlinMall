package com.kotlin.order.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

open interface OrderListView : BaseView {


    fun onGetOrderListResult(result: MutableList<Order>?)
    fun onConfirmOrderResult(result: Boolean)
    fun onCancelOrderResult(result: Boolean)


}