package com.sean.auth.data

object DataFactory {

    fun createData() {
        RoleData().run(null)
        UserData().run(null)
    }
}