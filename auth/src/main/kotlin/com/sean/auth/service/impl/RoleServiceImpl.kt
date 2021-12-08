package com.sean.auth.service.impl

import com.sean.auth.entity.Role
import com.sean.auth.repo.RoleRepo
import com.sean.auth.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl @Autowired constructor(
    private val rRepo: RoleRepo
): RoleService {

    override fun saveOne(role: Role): Role {
        return rRepo.save(role)
    }
}