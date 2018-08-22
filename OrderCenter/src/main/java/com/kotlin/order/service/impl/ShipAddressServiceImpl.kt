package com.kotlin.order.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.data.repository.ShipAddressRepository
import com.kotlin.order.service.ShipAddressService
import rx.Observable
import javax.inject.Inject

class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {


    @Inject
    lateinit var repository: ShipAddressRepository

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String)
            : Observable<Boolean> {
        return repository.addShipAddress(shipUserName, shipUserMobile, shipAddress).convertBoolean()
    }

    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return repository.getShipAddressList().convert()
    }

    override fun editShipAddress(address: ShipAddress): Observable<Boolean> {
        return repository.editShipAddress(address).convertBoolean()
    }

    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return repository.deleteShipAddress(id).convertBoolean()
    }

}