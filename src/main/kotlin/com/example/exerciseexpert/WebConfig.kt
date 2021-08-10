package com.example.exerciseexpert

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {

    @Autowired
    lateinit var requestInterceptor: RequestInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor)
            .addPathPatterns("/*")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login")
    }
}