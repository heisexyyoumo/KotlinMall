package com.kotlin.order.injection.module

import com.kotlin.order.service.ShipAddressService
import com.kotlin.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ShipAddressModule {

    @Provides
    fun providesShipAddressService(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }
}