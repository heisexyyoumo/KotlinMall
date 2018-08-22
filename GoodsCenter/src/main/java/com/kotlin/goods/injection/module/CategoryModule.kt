package com.kotlin.goods.injection.module

import com.kotlin.goods.service.CategoryService
import com.kotlin.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(categoryService : CategoryServiceImpl):CategoryService{
        return categoryService
    }
}