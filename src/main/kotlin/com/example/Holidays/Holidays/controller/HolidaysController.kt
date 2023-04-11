package com.example.Holidays.Holidays.controller

import com.example.Holidays.Holidays.model.Holiday
import com.example.Holidays.Holidays.service.HolidaysService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.IllegalArgumentException

@RestController
@RequestMapping("/api/holidays")
class HolidaysController(private val service: HolidaysService) {


    @ExceptionHandler(NoSuchElementException::class)
    fun recordNotFound(e:NoSuchElementException) :ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun recordAlreadyExists(e:IllegalArgumentException) :ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getHolidays(): Collection<Holiday> = service.getHolidays()

    @GetMapping("/{name}")
    fun getHoliday(@PathVariable name: String) :Holiday = service.getHoliday(name)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createHoliday(@RequestBody holiday: Holiday) :Holiday  = service.addHoliday(holiday)

    @PatchMapping
    fun updateHoliday(@RequestBody holiday: Holiday) :Holiday = service.updateHoliday(holiday)

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHoliday(@PathVariable name:String) = service.deleteHoliday(name)
}
