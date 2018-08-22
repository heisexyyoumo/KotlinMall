package com.kotlin.pay.injection.module

import com.kotlin.pay.service.PayService
import com.kotlin.pay.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

@Module
class PayModule {

    @Provides
    fun providesPayService(payService: PayServiceImpl): PayService {
        return payService
    }
}