package com.example.exerciseexpert.service

import com.example.exerciseexpert.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class AuthService @Autowired constructor() : UserDetailsService {
    val logger = LoggerFactory.getLogger(AuthService::class.java)

    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
        logger.info("Login attempt: $username, $user")
        return userRepository.findByEmail(username) ?:
            throw UsernameNotFoundException("User with email $username not found")
    }
}
