package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.presenter.view.GoodsListView
import com.kotlin.goods.service.GoodsService
import javax.inject.Inject

class GoodsListPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int) {
        /**
         * 业务逻辑
         */

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        goodsService.getGoodsList(categoryId, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)

    }

    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        /**
         * 业务逻辑
         */

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        goodsService.getGoodsListByKeyword(keyword, pageNo)
                .execute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
                    override fun onNext(t: MutableList<Goods>?) {
                        mView.onGetGoodsListResult(t)
                    }
                }, lifecycleProvider)

    }
}