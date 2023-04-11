package com.example.Holidays.Holidays.dataSource

import com.example.Holidays.Holidays.model.Holiday
import org.springframework.stereotype.Repository

class HolidayDataSourceImpl :HolidayDataSource {

    val holidaysList = mutableListOf<Holiday>(
        Holiday("New Year", "01-01-2023"),
        Holiday("Pongal", "14-01-2023")
    )

    override fun retrieveHolidays(): Collection<Holiday> {
        kotlin.TODO()
    }

    override fun retrieveHolidayByName(name: String): Holiday {
       kotlin.TODO()
         }

    override fun createHoliday(holiday: Holiday): Holiday {
       kotlin.TODO()
    }

}