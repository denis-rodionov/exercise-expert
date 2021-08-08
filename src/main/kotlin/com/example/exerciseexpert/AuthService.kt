package com.example.exerciseexpert

import com.example.exerciseexpert.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class AuthService @Autowired constructor() : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return User(username)
    }
}
