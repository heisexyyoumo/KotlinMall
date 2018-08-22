package com.kotlin.pay.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.pay.presenter.view.PayView
import com.kotlin.pay.service.PayService
import javax.inject.Inject

class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var payService: PayService

    fun getPaySign(orderId: Int, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        payService.getPaySign(orderId,totalPrice)
                .execute(object : BaseSubscriber<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onGetSignResult(t)
                    }
                }, lifecycleProvider)

    }

    fun payOrder(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        payService.payOrder(orderId)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onPayOrderResult(t)
                    }
                }, lifecycleProvider)

    }

}