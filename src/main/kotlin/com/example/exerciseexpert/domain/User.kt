package com.example.exerciseexpert.domain

import com.example.exerciseexpert.domain.emums.UserRole
import org.springframework.data.annotation.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.lang.Exception

data class User(
    @Id
    var id: String? = null,
    val name: String,
    val email: String,
    var userPassword: String,
    var role: UserRole,
    var supervisorUserId: String?,
) : UserDetails {
    override fun toString(): String {
        return "User $name, role: $role, id: $id"
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (role == UserRole.ADMIN) {
            return mutableListOf(
                SimpleGrantedAuthority(UserRole.ADMIN.toString()),
                SimpleGrantedAuthority(UserRole.TEACHER.toString()),
                SimpleGrantedAuthority(UserRole.STUDENT.toString())
            )
        } else if (role == UserRole.TEACHER) {
            return mutableListOf(
                SimpleGrantedAuthority(UserRole.TEACHER.toString()),
                SimpleGrantedAuthority(UserRole.STUDENT.toString())
            )
        } else if (role == UserRole.STUDENT) {
            return mutableListOf(SimpleGrantedAuthority(UserRole.STUDENT.toString()))
        } else {
            throw Exception("Unknown role $role")
        }
    }

    override fun getPassword(): String {
        return "{noop}$userPassword"
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