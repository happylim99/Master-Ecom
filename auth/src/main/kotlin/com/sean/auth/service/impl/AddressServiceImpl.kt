package com.sean.auth.service.impl

import com.sean.auth.entity.Address
import com.sean.auth.repo.AddressRepo
import com.sean.auth.service.AddressService
import com.sean.auth.service.Crud
import com.sean.auth.ui.req.AddrUptReq
import com.sean.auth.ui.res.AddrRes
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val repo: AddressRepo
): Crud<AddressService, Address, AddrRes, AddressRepo>(
    AddressService::class.java,
    Address::class.java,
    AddrRes::class.java
) {
    override fun getRepo() = repo

    override fun <T> getUpdateObj(req: T): Address? =
        repo.findByAddressId((req as AddrUptReq).addressId) ?: throw Exception("User not found")

}