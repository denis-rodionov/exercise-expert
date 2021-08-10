package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
    fun findByEmail(email: String): User?
}