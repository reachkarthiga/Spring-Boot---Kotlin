package com.example.Holidays.Holidays

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/endpoint")
class HelloWorldController {

    @GetMapping
    fun helloWorld() :String = "Heloo World! Reached the endpoint!"

}