package com.warriors.backend.utils

object Utils {
    const val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    const val passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"


    fun emailIsOk (mail : String) : Boolean{
        return mail.matches(emailRegex.toRegex())
    }
}