package com.example.Holidays.Holidays.dataSource

import com.example.Holidays.Holidays.model.Holiday
import org.springframework.stereotype.Repository

@Repository
class MockDataSource:HolidayDataSource {

    val holidaysList = mutableListOf<Holiday>(
        Holiday("New Year", "01-01-2023"),
        Holiday("Pongal", "14-01-2023")
    )

    override fun retrieveHolidays(): List<Holiday> {
        return holidaysList
    }

    override fun retrieveHolidayByName(name: String): Holiday {
        return holidaysList.firstOrNull {
            it.name == name
        } ?: throw NoSuchElementException("Cannot find such Element $name")

    }

    override fun createHoliday(holiday: Holiday): Holiday {

        if (holidaysList.any { it.name ==  holiday.name  }) {
            throw IllegalArgumentException("Holiday ${holiday.name} Already Added")
        }

        holidaysList.add(holiday)
        return holiday
    }


}