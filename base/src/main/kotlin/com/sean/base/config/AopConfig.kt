package com.sean.base.config

import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
@Slf4j
class AopConfig {

//    private val log: Logger = LoggerFactory.getLogger(javaClass)

//    @Before("execution(* com.sean.hr.controller.*.*(..))")
//    fun before() {
//        log.info("before")
//
//    }

//    @Around("execution(* com.sean.hr.controller.*.*(..))")
//    fun logController(joinPoint: ProceedingJoinPoint): Any {
//        return logExecutionTime(joinPoint)
//    }
//
//    @Around("@annotation(com.sean.hr.annotation.Log)")
//    fun logAnnotation(joinPoint: ProceedingJoinPoint): Any {
//        return logExecutionTime(joinPoint)
//    }


    @Around("execution(* com.sean.*.controller.*.*(..))")
    private fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any {
        val start = System.currentTimeMillis()
        val signature = joinPoint.signature.toShortString()
        val result = try {
            with(StringBuilder("Start -> Executing $signature")) {
//                appendParameters(joinPoint.args)
                log.info(toString())
            }
            joinPoint.proceed()
        } catch (throwable: Throwable) {
            if(throwable::class.simpleName.equals("CException")) {
                log.error("*** Checked Exception during executing $signature")
            } else {
                log.error("*** Exception during executing $signature", throwable)
            }
            throw throwable
        }
        val duration = System.currentTimeMillis() - start
        log.info("End -> Finished executing: $signature")
        log.info("Duration: $duration ms, Res: $result")
        return result
    }

    private fun StringBuilder.appendParameters(args: Array<Any>) {
        append("parameters: [")
        args.forEachIndexed { i, p ->
            append(p.javaClass.simpleName).append("(").append(p.toString()).append(")")
            if (i < args.size - 1) append(", ")
        }
        append("]")
    }

}