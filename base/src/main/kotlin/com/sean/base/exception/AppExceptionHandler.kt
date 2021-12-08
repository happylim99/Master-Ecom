package com.sean.base.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.*

@ControllerAdvice
class AppExceptionHandler {

//    @ExceptionHandler(value = [UserServiceException::class])
//    fun handleUserServiceException(ex: UserServiceException, request: WebRequest?): ResponseEntity<Any?>? {
//        val errorMessage = ErrorMsg(msg = ex.message!!)
//        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
//    }

    @ExceptionHandler(value = [CException::class])
    fun handleOtherException(ex: Exception, request: WebRequest?): ResponseEntity<Any?>? {
        val errorMessage = ErrorMsg(msg = ex.message!!)
        return ResponseEntity(errorMessage, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}