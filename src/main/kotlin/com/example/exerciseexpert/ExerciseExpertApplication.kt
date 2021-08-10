package com.example.exerciseexpert

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
class ExerciseExpertApplication

fun main(args: Array<String>) {
    runApplication<ExerciseExpertApplication>(*args)
}
