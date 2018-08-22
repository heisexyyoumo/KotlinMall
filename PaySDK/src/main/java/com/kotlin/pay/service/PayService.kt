package com.kotlin.pay.service

import com.kotlin.base.data.protocol.BaseResp
import rx.Observable


interface PayService {
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    fun payOrder(orderId: Int): Observable<Boolean>

}