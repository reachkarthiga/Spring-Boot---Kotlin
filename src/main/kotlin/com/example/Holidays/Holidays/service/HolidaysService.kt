package com.example.Holidays.Holidays.service

import com.example.Holidays.Holidays.dataSource.HolidayDataSource
import com.example.Holidays.Holidays.model.Holiday
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.swing.text.html.Option
import javax.transaction.Transactional
import kotlin.NoSuchElementException

@Service
class HolidaysService(private val holidayDataSource: HolidayDataSource) {

    fun getHolidays(): Collection<Holiday> {
        return holidayDataSource.findAll()
    }

    fun getHoliday(holidayName: String): Optional<Holiday> {
        val holiday = holidayDataSource.getHolidayByName(holidayName)
        if (!holiday.isPresent) {
            throw NoSuchElementException("Cannot find $holidayName")
        }
        return holiday
    }

    fun addHoliday(holiday: Holiday): Holiday {

        holiday.holidayName?.let {
            val checkHoliday = holidayDataSource.getHolidayByName(it)

            if (checkHoliday.isPresent) {
                throw IllegalArgumentException("$holiday.holidayName already Added")
            }
        }

        return holidayDataSource.save(holiday)

    }

    fun updateHoliday(holiday: Holiday): Holiday {

        if (holiday.id?.let { holidayDataSource.existsById(it) } == true) {
            holidayDataSource.save(holiday)
            return holiday
        } else {
            throw NoSuchElementException("Cannot find ${holiday.holidayName}")
        }

    }

    @Transactional
    fun deleteHoliday(name: String): Any {

        val holiday = holidayDataSource.getHolidayByName(name)

        if (!holiday.isPresent) {
            throw NoSuchElementException("Cannot find $name")
        }

        return holidayDataSource.deleteByName(name)
    }

}