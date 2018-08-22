package com.kotlin.goods.presenter

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.presenter.view.CategoryView
import com.kotlin.goods.service.CategoryService
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    fun getCategory(parentId:Int) {
        /**
         * 业务逻辑
         */

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()

        categoryService.getCategory(parentId)
                .execute(object : BaseSubscriber<MutableList<Category>?>(mView) {
                    override fun onNext(t: MutableList<Category>?) {
                        mView.onGetCategoryResult(t)
                    }
                }, lifecycleProvider)

    }
}