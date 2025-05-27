package com.warriors.backend.users.serverside.dto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import java.time.LocalDate
import java.util.UUID


@Entity
@Table(name = "Users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(unique=true)
    val userUsername: String,
    @Column(unique = true)
    val email: String,
    val userPassword: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val validated: Boolean,
    val authorities : Set<String> = setOf()
) :UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String = email
}