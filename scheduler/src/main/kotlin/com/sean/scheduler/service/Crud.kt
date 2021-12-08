package com.sean.scheduler.service

interface Crud<T, CRT, UPT> {
    fun createOne(req: CRT): T
    fun updateOne(uid: String, req: UPT): T
    fun showOne(uid: String): T?
    fun showAllValid(): List<T>?
    fun showAllVoid(): List<T>?
    fun showAll(): List<T>?
    fun deleteOne(uid: String): String
}