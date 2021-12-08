package com.sean.auth.service

import com.sean.auth.entity.Role
import com.sean.auth.entity.User

interface RoleService {

    fun saveOne(role: Role): Role
}