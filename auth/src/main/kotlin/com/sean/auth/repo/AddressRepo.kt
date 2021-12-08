package com.sean.auth.repo

import com.sean.auth.entity.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepo: JpaRepository<Address, Long> {

    fun findByAddressId(addressId: String): Address?
}