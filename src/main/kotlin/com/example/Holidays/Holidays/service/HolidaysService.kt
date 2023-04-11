package com.example.Holidays.Holidays.service

import com.example.Holidays.Holidays.dataSource.HolidayDataSource
import com.example.Holidays.Holidays.model.Holiday
import org.springframework.stereotype.Service

@Service
class HolidaysService(private val holidayDataSource: HolidayDataSource) {

    fun getHolidays(): Collection<Holiday> {
        return holidayDataSource.retrieveHolidays()
    }

    fun getHoliday(name: String): Holiday {
        return holidayDataSource.retrieveHolidayByName(name)
    }

    fun addHoliday(holiday: Holiday): Holiday {
        return holidayDataSource.createHoliday(holiday)
    }

    fun updateHoliday(holiday: Holiday): Holiday {
        return holidayDataSource.updateHoliday(holiday)
    }

    fun deleteHoliday(name: String): Any {
        return holidayDataSource.deleteHoliday(name)
    }

}