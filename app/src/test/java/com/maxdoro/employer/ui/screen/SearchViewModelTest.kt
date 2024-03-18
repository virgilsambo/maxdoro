package com.maxdoro.employer.ui.screen

import com.google.common.truth.Truth.assertThat
import com.maxdoro.employer.MainDispatcherRule
import com.maxdoro.employer.common.SortCriteria
import com.maxdoro.employer.domain.GetEmployerSearchResultsUseCase
import com.maxdoro.employer.model.Employer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.collections.immutable.persistentListOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Class under test
    private lateinit var viewModel: SearchViewModel

    @MockK
    private lateinit var getEmployerSearchResultsUseCase: GetEmployerSearchResultsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = SearchViewModel(
            getEmployerSearchResultsUseCase = getEmployerSearchResultsUseCase
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given ViewModel is initialized, it should have an empty search query`() {
        // Arrange
        val expectedSearchQuery = ""

        // Act

        // Assert
        assertThat(viewModel.searchQuery).isEqualTo(expectedSearchQuery)
    }

    @Test
    fun `Given search query is empty, it should return an empty search results list`() {
        // Arrange
        val expectedUiState = SearchUiState()

        // Act
        viewModel.updateSearchQuery("")
        viewModel.initialiseUiState()
        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertThat(viewModel.uiState.value).isEqualTo(expectedUiState)
    }

    @Test
    fun `Given search query results return success, it should have success UI state`() {
        // Arrange
        val searchQuery = "Ju"

        val searchResults = persistentListOf(
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

        val expectedUiState = SearchUiState(
            searchResults = searchResults
        )

        coEvery { getEmployerSearchResultsUseCase(searchQuery) } returns
                com.maxdoro.employer.common.Result.Success(
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
        viewModel.updateSearchQuery(searchQuery)
        viewModel.initialiseUiState()
        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertThat(viewModel.uiState.value).isEqualTo(expectedUiState)
    }

    @Test
    fun `Given search query results return success with an empty list, it should set the no results flag`() {
        // Arrange
        val searchQuery = "asdedcs"

        val searchResults = persistentListOf<Employer>()

        val expectedUiState = SearchUiState(
            searchResults = searchResults,
            queryHasNoResults = true
        )

        coEvery {
            getEmployerSearchResultsUseCase(searchQuery)
        } returns com.maxdoro.employer.common.Result.Success(emptyList())

        // Act
        viewModel.updateSearchQuery(searchQuery)
        viewModel.initialiseUiState()
        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertThat(viewModel.uiState.value).isEqualTo(expectedUiState)
    }

    @Test
    fun `Given updating search query, it should update the search query value`() {
        // Arrange
        val searchQuery = "jum"

        coEvery { getEmployerSearchResultsUseCase(searchQuery) } returns com.maxdoro.employer.common.Result.Success(emptyList())

        // Act
        viewModel.updateSearchQuery(searchQuery)

        // Assert
        assertThat(viewModel.searchQuery).isEqualTo(searchQuery)
    }

    @Test
    fun `Given sorting search query results by discount, it should update the search query value`() {
        // Arrange
        val searchQuery = "Ju"

        val searchResults = persistentListOf(
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

        val expectedUiState = SearchUiState(
            sortCriteria = SortCriteria.DiscountPercentage,
            searchResults = searchResults
        )

        coEvery { getEmployerSearchResultsUseCase(searchQuery) } returns
                com.maxdoro.employer.common.Result.Success(
                    listOf(
                        Employer(
                            _employerId = 1234,
                            _name = "Junior Einstein",
                            _discountPercentage = 10,
                            _place = "LEUSDEN"
                        ),
                        Employer(
                            _employerId = 1235,
                            _name = "Jumbo",
                            _discountPercentage = 8,
                            _place = "AMSTERDAM"
                        ),

                    )
                )

        // Act
        viewModel.updateSearchQuery(searchQuery)
        viewModel.initialiseUiState()
        viewModel.updateSortCriteria(SortCriteria.DiscountPercentage)
        mainDispatcherRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertThat(viewModel.uiState.value).isEqualTo(expectedUiState)
    }
}