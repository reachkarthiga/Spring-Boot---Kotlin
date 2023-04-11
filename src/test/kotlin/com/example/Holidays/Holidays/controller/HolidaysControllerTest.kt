package com.example.Holidays.Holidays.controller

import com.example.Holidays.Holidays.model.Holiday
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class HolidaysControllerTest @Autowired constructor(
    val mockMvc: MockMvc, val objectMapper: ObjectMapper
) {


    private val baseURL = "/api/holidays"

    @Nested
    @DisplayName("GET /api/holidays")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetAllBank {
        @Test
        fun should_return_all_holidays() {

            mockMvc.get(baseURL).andDo {
                println()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].name") { value("New Year") }
            }

        }
    }

    @Nested
    @DisplayName("GET /api/holidays/{name}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetOneBank {

        @Test
        fun should_return_holiday_by_name() {

            val name = "Pongal"
            mockMvc.get("$baseURL/$name").andDo {
                println()
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Pongal") }
            }

        }

        @Test
        fun should_return_holiday_no_record() {

            val name = "xxx"
            mockMvc.get("$baseURL/$name").andDo {
                println()
            }.andExpect {
                status { isNotFound() }
            }

        }

    }

    @Nested
    @DisplayName("POST /api/holidays")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostHoliday {

        @Test
        fun createHolidays() {
            val newHoliday = Holiday("Tamil New Year", "14-04-2023")

            mockMvc.post(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newHoliday)
            }
                .andDo {
                    print()
                }.andExpect {
                    status { isCreated() }
                }

        }

        @Test
        fun createHolidays_BadRequest() {
            val newHoliday = Holiday("New Year", "01-01-2023")

            mockMvc.post(baseURL) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newHoliday)
            }
                .andDo {
                    print()
                }.andExpect {
                    status { isBadRequest() }
                }

        }
    }
}

