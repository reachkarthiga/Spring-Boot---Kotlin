package com.example.Holidays.Holidays.model

import java.util.*
import javax.persistence.*

@Entity
@Table
data class Holiday(

    @Id
    @SequenceGenerator(name = "holiday_sequence",
    sequenceName = "holiday_sequence",
    allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    var id :Long? = null,

    var holidayName :String? = null,
    var date: String? = null

) {

}
