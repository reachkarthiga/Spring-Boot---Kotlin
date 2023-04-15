package com.example.Holidays.Holidays.service

import com.example.Holidays.Holidays.dataSource.HolidayDataSource
import com.example.Holidays.Holidays.model.Holiday
import org.apache.commons.logging.Log
import org.hibernate.InvalidMappingException
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger
import javax.swing.text.html.Option
import javax.transaction.Transactional
import kotlin.NoSuchElementException

@Service
class HolidaysService(private val holidayDataSource: HolidayDataSource) {

    val sdf = SimpleDateFormat("yyyy-MM-dd")


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
                throw IllegalArgumentException("Holiday already Added")
            }
        }

        try {
            sdf.parse(holiday.date)
        } catch (e:Exception) {
           throw NumberFormatException("Date should be in yyyy-MM-dd format")
        }

        return holidayDataSource.save(holiday)

    }

    fun updateHoliday(holiday: Holiday): Holiday {

        if (holiday.id?.let { holidayDataSource.existsById(it) } == true) {

            try {
                sdf.parse(holiday.date)
            } catch (e:Exception) {
                throw NumberFormatException("Date should be in yyyy-MM-dd format")
            }

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

    fun getHolidayByDate(date: String): Collection<Holiday>{
        LoggerFactory.getLogger(HolidaysService::class.java).info("$date")
        return holidayDataSource.getHolidayByDate(date)
    }

}