package com.example.Holidays.Holidays.dataSource

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockDataSourceTest {

    private val mockDataSource = MockDataSource()

    @Test
    fun should_return_mock_data() {
        val holidays = mockDataSource.retrieveHolidays()
        assertThat(holidays.size).isEqualTo(2)

        assertThat(holidays).allMatch { it.date.isNotEmpty() }
        assertThat(holidays).allMatch { it.holidayName.isNotEmpty() }

    }
}