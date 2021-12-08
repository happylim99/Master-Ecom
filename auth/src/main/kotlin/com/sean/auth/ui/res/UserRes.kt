package com.sean.auth.ui.res

import com.sean.auth.entity.Role
import org.springframework.hateoas.RepresentationModel

data class UserRes(
    var userId: String = "",
    var name: String = "",
    var username: String = "",
    var email: String = "",
    var roles: MutableList<Role> = mutableListOf()
): RepresentationModel<UserRes>()