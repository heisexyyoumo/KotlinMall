package com.kotlin.goods.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.protocol.Category

open interface CartListView : BaseView {

    fun onGetCartListResult(result : MutableList<CartGoods>?)
    fun onDeleteCartListResult(result : Boolean)
    fun onSubmitCartListResult(result : Int)

}