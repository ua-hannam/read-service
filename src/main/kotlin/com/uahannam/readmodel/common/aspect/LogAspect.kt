package com.uahannam.readmodel.common.aspect

import com.uahannam.readmodel.common.utils.CurrentTimeGenerator
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LogAspect {

    private val logger = LoggerFactory.getLogger(LogAspect::class.java)

    @Pointcut("execution(* com.uahannam.readmodel.kafka..*.*(..))")
    fun sendMessageBeforeExecute() { /* 로깅을 위한 Target을 정하는 함수이므로 Body 불필요 */}

    @Before("sendMessageBeforeExecute()")
    fun loggingBeforeSendMessage(joinPoint: JoinPoint) {
        val method = getMethod(joinPoint)

        logger.info("${method.name}() 메시지 전송 시작")
        logger.info("${method.name}() 메시지 전송 시간 => {}", CurrentTimeGenerator.generateCurrentTime())

        val paramArgs = joinPoint.args

        for (arg in paramArgs) {
            paramArgs.let {
                logger.info("Parameter Type => {}", arg.javaClass.simpleName)
                logger.info("Parameter Value => {}", arg)
            }
        }
    }

    @AfterReturning("sendMessageBeforeExecute()")
    fun loggingAfterSendMessage(joinPoint: JoinPoint) {
        val method = getMethod(joinPoint)

        logger.info("메시지 전송 종료 시간 => {}", CurrentTimeGenerator.generateCurrentTime())
        logger.info("메시지 전송 종료, 종료된 함수 => {}", method.name)
    }

    private fun getMethod(joinPoint: JoinPoint) =
        (joinPoint.signature as MethodSignature).method

}