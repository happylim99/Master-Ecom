package com.sean.auth.data

import com.sean.auth.entity.Role
import com.sean.auth.service.UserService
import com.sean.auth.service.impl.UserServiceImpl
import com.sean.auth.ui.req.UserCrtReq
import com.sean.base.util.SpringContext
import org.springframework.boot.CommandLineRunner

//@Component
//@Order(2)
class UserData(
    private val usrSrv: UserService = SpringContext.getBean(UserServiceImpl::class.java)
): CommandLineRunner {

    override fun run(vararg args: String?) {
        val userRes1 = usrSrv.createOne(UserCrtReq("aaa", "aaa", "aaa@aaa.com", "abcd"))
        val userRes2 = usrSrv.createOne(UserCrtReq("bbb", "bbb", "bbb@bbb.com", "abcd"))
        val userRes3 = usrSrv.createOne(UserCrtReq("ccc", "ccc", "ccc@ccc.com", "abcd"))
        val userRes4 = usrSrv.createOne(UserCrtReq("ddd", "ddd", "ddd@ddd.com", "abcd"))
        usrSrv.createOne(UserCrtReq("eee", "eee", "eee@eee.com", "abcd"))
        usrSrv.createOne(UserCrtReq("fff", "fff", "fff@fff.com", "abcd"))
        usrSrv.createOne(UserCrtReq("ggg", "ggg", "ggg@ggg.com", "abcd"))
        usrSrv.createOne(UserCrtReq("hhh", "hhh", "hhh@hhh.com", "abcd"))

//        usrSrv.savePure(User("aaa", "aaa@aaa.com", "abcd"))
//        usrSrv.savePure(User("bbb", "bbb@bbb.com", "abcd"))
//        usrSrv.savePure(User("ccc", "ccc@ccc.com", "abcd"))
//        usrSrv.savePure(User("ddd", "ddd@ddd.com", "abcd"))

        usrSrv.addRole(userRes1.uid, Role.RoleName.root.name)
        usrSrv.addRole(userRes2.uid, Role.RoleName.admin.name)
        usrSrv.addRole(userRes3.uid, Role.RoleName.superuser.name)
        usrSrv.addRole(userRes4.uid, Role.RoleName.user.name)
    }
}