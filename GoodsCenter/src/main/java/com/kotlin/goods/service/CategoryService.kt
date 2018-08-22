package com.kotlin.goods.service

import com.kotlin.goods.data.protocol.Category
import rx.Observable


interface CategoryService {

    fun getCategory(parentId:Int):Observable<MutableList<Category>?>
}