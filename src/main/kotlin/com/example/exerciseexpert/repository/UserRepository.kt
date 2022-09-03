package com.example.exerciseexpert.repository

import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.emums.UserRole
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
    fun findByEmail(email: String): User?
    fun findByRole(roleName: UserRole): List<User>
}