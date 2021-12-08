package com.sean.auth.data

import com.sean.auth.entity.Role
import com.sean.auth.service.RoleService
import com.sean.auth.service.impl.RoleServiceImpl
import com.sean.base.util.SpringContext
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

//@Component
//@Order(1)
class RoleData(
    private val roleSrv: RoleService = SpringContext.getBean(RoleServiceImpl::class.java)
): CommandLineRunner {

    override fun run(vararg args: String?) {
        roleSrv.saveOne(Role(Role.RoleName.root.name))
        roleSrv.saveOne(Role(Role.RoleName.admin.name))
        roleSrv.saveOne(Role(Role.RoleName.superuser.name))
        roleSrv.saveOne(Role(Role.RoleName.user.name))
    }
}