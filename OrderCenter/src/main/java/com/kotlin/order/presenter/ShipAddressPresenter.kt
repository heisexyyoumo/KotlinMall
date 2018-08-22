package com.kotlin.order.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.presenter.view.ShipAddressView
import com.kotlin.order.service.ShipAddressService
import javax.inject.Inject

class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList()
                .execute(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
                    override fun onNext(t: MutableList<ShipAddress>?) {
                        mView.onGetShipAddressResult(t)
                    }
                }, lifecycleProvider)

    }

    fun setDefaultShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onSetDefaultResult(t)
                    }
                }, lifecycleProvider)

    }


    fun deleteShipAddress(id: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onDeleteResult(t)
                    }
                }, lifecycleProvider)

    }

}