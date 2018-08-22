package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderConfirmView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

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

    fun submitOrder(order: Order) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onSubmitOrderResult(t)
                    }
                }, lifecycleProvider)

    }

}