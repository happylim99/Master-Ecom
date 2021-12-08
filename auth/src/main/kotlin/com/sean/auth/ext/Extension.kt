package com.sean.auth.ext

import org.springframework.data.repository.CrudRepository
import java.util.*

fun String.toBase64(): String {
    return Base64.getEncoder().encodeToString(this.toByteArray())
}

fun <T, ID> CrudRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)