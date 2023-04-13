package com.example.Holidays.Holidays.dataSource

import com.example.Holidays.Holidays.model.Holiday
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HolidayDataSource :JpaRepository<Holiday, Long>
{
    @Query("SELECT h FROM Holiday h WHERE h.holidayName = :name")
    fun getHolidayByName(name:String) :Optional<Holiday>

    @Modifying
    @Query("DELETE FROM Holiday h WHERE h.holidayName = :name")
    fun deleteByName(name:String) :Unit


}