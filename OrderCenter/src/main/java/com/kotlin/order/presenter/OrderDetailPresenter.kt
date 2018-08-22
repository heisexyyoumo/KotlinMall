package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderConfirmView
import com.kotlin.order.presenter.view.OrderDetailView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var orderService: OrderService

    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId)
                .execute(object : BaseSubscriber<Order>(mView) {
                    override fun onNext(t: Order) {
                        mView.onGetOrderByIdResult(t)
                    }
                }, lifecycleProvider)

    }

}