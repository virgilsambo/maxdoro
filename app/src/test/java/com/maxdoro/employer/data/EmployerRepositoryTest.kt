package com.maxdoro.employer.data

import com.google.common.truth.Truth.assertThat
import com.maxdoro.employer.MainDispatcherRule
import com.maxdoro.employer.data.local.EmployerLocalDataSource
import com.maxdoro.employer.data.remote.EmployerNetworkDataSource
import com.maxdoro.employer.data.remote.EmployerRepository
import com.maxdoro.employer.data.remote.EmployerRepositoryImpl
import com.maxdoro.employer.model.Employer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class EmployerRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var employerRepository: EmployerRepository

    @MockK
    private lateinit var networkDataSource: EmployerNetworkDataSource
    @MockK
    private lateinit var localDataSource: EmployerLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        employerRepository = EmployerRepositoryImpl(
            networkDataSource = networkDataSource,
            localDataSource = localDataSource,
            ioDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given valid employer search results data, it should return success`() = runTest {
        // Arrange
        val searchQuery = "ju"

        val expectedResult = com.maxdoro.employer.common.Result.Success(
            listOf(
                Employer(
                    _employerId = 1235,
                    _name = "Jumbo",
                    _discountPercentage = 8,
                    _place = "AMSTERDAM"
                ),
                Employer(
                    _employerId = 1234,
                    _name = "Junior Einstein",
                    _discountPercentage = 10,
                    _place = "LEUSDEN"
                )
            )
        )

        coEvery {
            networkDataSource.getEmployerSearchResults(searchQuery)
        } returns
                Response.success(
                    listOf(
                        Employer(
                            _employerId = 1235,
                            _name = "Jumbo",
                            _discountPercentage = 8,
                            _place = "AMSTERDAM"
                        ),
                        Employer(
                            _employerId = 1234,
                            _name = "Junior Einstein",
                            _discountPercentage = 10,
                            _place = "LEUSDEN"
                        )
                    )
                )

        // Act
        val result = employerRepository.getEmployerSearchResults(searchQuery)

        // Assert
        assertThat(result).isInstanceOf(com.maxdoro.employer.common.Result.Success::class.java)
        assertThat((result as com.maxdoro.employer.common.Result.Success).data).isEqualTo(expectedResult.data)
    }


}