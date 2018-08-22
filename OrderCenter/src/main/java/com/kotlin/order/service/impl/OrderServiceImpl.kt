package com.kotlin.order.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.order.data.protocol.Order
import com.kotlin.order.data.repository.OrderRepository
import com.kotlin.order.service.OrderService
import rx.Observable
import javax.inject.Inject

class OrderServiceImpl @Inject constructor() : OrderService {


    @Inject
    lateinit var repository: OrderRepository


    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }


    override fun submitOrder(order: Order): Observable<Boolean> {

        return repository.submitOrder(order).convertBoolean()
    }

    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return repository.getOrderList(orderStatus).convert()
    }

    override fun cancelOrder(orderId: Int): Observable<Boolean> {
        return repository.cancelOrder(orderId).convertBoolean()

    }

    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return repository.confirmOrder(orderId).convertBoolean()
    }

}