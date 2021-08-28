package com.example.exerciseexpert.domain

import com.example.exerciseexpert.domain.emums.UserRole
import org.springframework.data.annotation.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class User(
    @Id
    var id: String? = null,
    val name: String,
    val email: String,
    var userPassword: String,
    var role: UserRole?
) : UserDetails {
    override fun toString(): String {
        return "User $name, role: $role, password: $password"
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return "{noop}qwe"
    }

    override fun getUsername(): String {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}