package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.goods.data.protocol.Category
import com.kotlin.goods.data.repository.CategoryRepository
import com.kotlin.goods.service.CategoryService
import rx.Observable
import java.util.*
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor() : CategoryService {


    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {

        return repository.getCategory(parentId).convert()
    }

}