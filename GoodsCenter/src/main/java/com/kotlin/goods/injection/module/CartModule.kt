package com.kotlin.goods.injection.module

import com.kotlin.goods.service.CartService
import com.kotlin.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

@Module
class CartModule {

    @Provides
    fun providesCartService(cartService: CartServiceImpl): CartService {
        return cartService
    }
}