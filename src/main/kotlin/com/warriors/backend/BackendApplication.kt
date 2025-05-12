package com.warriors.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@ComponentScan(basePackages = ["com.warriors.backend.users"])
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
