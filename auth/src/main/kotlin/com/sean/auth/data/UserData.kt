package com.sean.auth.data

import com.sean.auth.entity.Role
import com.sean.auth.service.UserService
import com.sean.auth.service.impl.UserServiceImpl
import com.sean.auth.ui.req.UserCreateReq
import com.sean.base.util.SpringContext
import org.springframework.boot.CommandLineRunner

//@Component
//@Order(2)
class UserData(
    private val usrSrv: UserService = SpringContext.getBean(UserServiceImpl::class.java)
): CommandLineRunner {

    override fun run(vararg args: String?) {
        val userRes1 = usrSrv.createOne(UserCreateReq("aaa", "aaa", "aaa@aaa.com", "abcd"))
        val userRes2 = usrSrv.createOne(UserCreateReq("bbb", "bbb", "bbb@bbb.com", "abcd"))
        val userRes3 = usrSrv.createOne(UserCreateReq("ccc", "ccc", "ccc@ccc.com", "abcd"))
        val userRes4 = usrSrv.createOne(UserCreateReq("ddd", "ddd", "ddd@ddd.com", "abcd"))
        usrSrv.createOne(UserCreateReq("eee", "eee", "eee@eee.com", "abcd"))
        usrSrv.createOne(UserCreateReq("fff", "fff", "fff@fff.com", "abcd"))
        usrSrv.createOne(UserCreateReq("ggg", "ggg", "ggg@ggg.com", "abcd"))
        usrSrv.createOne(UserCreateReq("hhh", "hhh", "hhh@hhh.com", "abcd"))

//        usrSrv.savePure(User("aaa", "aaa@aaa.com", "abcd"))
//        usrSrv.savePure(User("bbb", "bbb@bbb.com", "abcd"))
//        usrSrv.savePure(User("ccc", "ccc@ccc.com", "abcd"))
//        usrSrv.savePure(User("ddd", "ddd@ddd.com", "abcd"))

        usrSrv.addRole(userRes1.userId, Role.RoleName.root.name)
        usrSrv.addRole(userRes2.userId, Role.RoleName.admin.name)
        usrSrv.addRole(userRes3.userId, Role.RoleName.superuser.name)
        usrSrv.addRole(userRes4.userId, Role.RoleName.user.name)
    }
}