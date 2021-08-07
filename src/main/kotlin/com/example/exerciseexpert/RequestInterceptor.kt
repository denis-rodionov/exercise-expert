package com.example.exerciseexpert

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestInterceptor() : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(RequestInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val startTime = Instant.now().toEpochMilli()
        logger.info("Request URL::" + request.getRequestURL().toString() +
                ":: Start Time=" + Instant.now());
        request.setAttribute("startTime", startTime)
        return true
    }
}