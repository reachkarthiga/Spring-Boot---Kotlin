package com.example.Holidays.Holidays.dataSource

import com.example.Holidays.Holidays.model.Holiday

interface HolidayDataSource {

    fun retrieveHolidays(): Collection<Holiday>
    fun retrieveHolidayByName(name: String): Holiday
    fun createHoliday(holiday: Holiday): Holiday
    fun updateHoliday(holiday: Holiday): Holiday
    fun deleteHoliday(holidayName: String)
}