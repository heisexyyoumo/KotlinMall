package com.kotlin.order.injection.module

import com.kotlin.order.service.OrderService
import com.kotlin.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

@Module
class OrderModule {

    @Provides
    fun providesOrderService(orderService: OrderServiceImpl): OrderService {
        return orderService
    }
}