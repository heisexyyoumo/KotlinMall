package com.kotlin.order.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.order.data.protocol.Order

open interface EditShipAddressView : BaseView {

    fun onAddShipAddressResult(result: Boolean)
    fun onEditShipAddressResult(result: Boolean)


}