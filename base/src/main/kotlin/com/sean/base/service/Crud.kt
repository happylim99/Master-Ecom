package com.sean.base.service

interface Crud<RES, CRT, UPT> {
    fun createOne(req: CRT): RES
    fun updateOne(uid: String, req: UPT): RES
    fun showOne(uid: String): RES?
    fun showAllValid(): List<RES>?
    fun showAllVoid(): List<RES>?
    fun showAll(): List<RES>?
    fun deleteOne(uid: String): String
}