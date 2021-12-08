package com.sean.auth.service

import com.sean.auth.ui.req.AddrCreateReq
import com.sean.auth.ui.req.AddrUptReq
import com.sean.auth.ui.res.AddrRes

interface AddressService {

    fun createOne(addrReq: AddrCreateReq): AddrRes
    fun updateOne(userReq: AddrUptReq): AddrRes
    fun showOne(id: String): AddrRes?
    fun showAll(): List<AddrRes>

}