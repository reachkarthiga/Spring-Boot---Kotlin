package com.example.Holidays.Holidays.controller

import com.example.Holidays.Holidays.model.Holiday
import com.example.Holidays.Holidays.service.HolidaysService
import org.hibernate.InvalidMappingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.IllegalArgumentException
import kotlin.NoSuchElementException

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

    @ExceptionHandler(NumberFormatException::class)
    fun dateFormatError(e:NumberFormatException) :ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getHolidays(): Collection<Holiday> = service.getHolidays()

    @GetMapping("/{holidayName}")
    fun getHoliday(@PathVariable holidayName: String) : Optional<Holiday> = service.getHoliday(holidayName)

    @GetMapping("/date")
    fun getHolidayByDate(@RequestParam ("date") date: String) : Collection<Holiday> = service.getHolidayByDate(date)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createHoliday(@RequestBody holiday: Holiday) :Holiday  = service.addHoliday(holiday)

    @PutMapping
    fun updateHoliday( @RequestBody holiday: Holiday) :Holiday = service.updateHoliday(holiday)

    @DeleteMapping("/{holidayName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHoliday(@PathVariable holidayName: String) = service.deleteHoliday(holidayName)
}
