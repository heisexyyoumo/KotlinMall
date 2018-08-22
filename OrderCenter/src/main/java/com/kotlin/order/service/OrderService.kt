package com.kotlin.order.service

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.order.data.api.OrderApi
import com.kotlin.order.data.protocol.CancelOrderReq
import com.kotlin.order.data.protocol.Order
import rx.Observable


interface OrderService {

    fun getOrderById(orderId: Int): Observable<Order>

    fun submitOrder(order: Order): Observable<Boolean>

    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>

    fun cancelOrder(orderId: Int): Observable<Boolean>

    fun confirmOrder(orderId: Int): Observable<Boolean>

}