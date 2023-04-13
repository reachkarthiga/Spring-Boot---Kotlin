package com.example.Holidays.Holidays.dataSource

import com.example.Holidays.Holidays.model.Holiday
import org.springframework.stereotype.Repository

class MockDataSource {

    val holidaysList = mutableListOf<Holiday>(

    )

     fun retrieveHolidays(): List<Holiday> {
        return holidaysList
    }

     fun retrieveHolidayByName(name: String): Holiday {
        return holidaysList.firstOrNull {
            it.holidayName == name
        } ?: throw NoSuchElementException("Cannot find such Element $name")

    }

     fun createHoliday(holiday: Holiday): Holiday {

        if (holidaysList.any { it.holidayName ==  holiday.holidayName  }) {
            throw IllegalArgumentException("Holiday ${holiday.holidayName} Already Added")
        }

        holidaysList.add(holiday)
        return holiday
    }


     fun updateHoliday(holiday: Holiday): Holiday {

        val toReplace = (holidaysList.firstOrNull { it.holidayName == holiday.holidayName }) ?: throw NoSuchElementException("Cannot find such Element ${holiday.holidayName}")

        holidaysList.remove(toReplace)
        holidaysList.add(holiday)

        return holiday

    }

     fun deleteHoliday(holidayName: String) {

        val toDelete = (holidaysList.firstOrNull { it.holidayName == holidayName }) ?: throw NoSuchElementException("Cannot find such Element $holidayName")
        holidaysList.remove(toDelete)

    }


}