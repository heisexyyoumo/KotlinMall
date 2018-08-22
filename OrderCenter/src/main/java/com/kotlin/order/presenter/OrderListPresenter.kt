package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.presenter.view.OrderListView
import com.kotlin.order.service.OrderService
import javax.inject.Inject

class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var orderService: OrderService


    fun getOrderList(orderStatus: Int){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus)
                .execute(object : BaseSubscriber<MutableList<Order>?>(mView) {
                    override fun onNext(t: MutableList<Order>?) {
                        mView.onGetOrderListResult(t)
                    }
                }, lifecycleProvider)

    }

    fun confirmOrder(orderId: Int){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onConfirmOrderResult(t)
                    }
                }, lifecycleProvider)

    }

    fun cancelOrder(orderId: Int){
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onCancelOrderResult(t)
                    }
                }, lifecycleProvider)

    }


}