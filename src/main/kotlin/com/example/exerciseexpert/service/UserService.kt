package com.example.exerciseexpert.service

import com.example.exerciseexpert.domain.User
import com.example.exerciseexpert.domain.emums.UserRole
import com.example.exerciseexpert.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun getSupervisors(): List<User> {
        val admins = userRepository.findByRole(UserRole.ADMIN)
        val teachers = userRepository.findByRole(UserRole.TEACHER)

        return admins + teachers
    }
}