package com.warriors.backend.users.serverside.dto

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

import java.time.LocalDate
import java.util.UUID


@Entity
@Table(name = "Users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(unique=true)
    val username: String,
    @Column(unique = true)
    val email: String,
    val password: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val validated: Boolean
) 