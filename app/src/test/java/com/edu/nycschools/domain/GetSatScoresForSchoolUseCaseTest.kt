package com.edu.nycschools.domain

import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.sat.SatScore
import com.edu.nycschools.data.repository.SchoolsRepository
import com.edu.nycschools.domain.sat_scores.GetSatScoresForSchoolUseCase
import com.edu.nycschools.domain.sat_scores.GetSatScoresForSchoolUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSatScoresForSchoolUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var getSatScoresForSchoolUseCase: GetSatScoresForSchoolUseCase

    @MockK
    lateinit var schoolsRepositoryMock: SchoolsRepository

    private val fakeSatScore = listOf(
        SatScore(
            dbn = "10X439",
            numOfSatTestTakers = "122",
            satCriticalReadingAvgScore = "234",
            satMathAvgScore = "212",
            satWritingAvgScore = "212",
            schoolName = "fake_school"
        )
    )

    companion object {
        const val FAKE_DBN = "WASAS"
    }

    @Before
    fun setUp() {
        coEvery { schoolsRepositoryMock.getSatScoresForSchool(FAKE_DBN) } returns ResultWrapper.Success(
            fakeSatScore
        )
        getSatScoresForSchoolUseCase =
            GetSatScoresForSchoolUseCaseImpl(schoolsRepository = schoolsRepositoryMock)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get sat score call is not made on launch`() = runTest {
        //verify no calls happened
        coVerify(exactly = 0) { schoolsRepositoryMock.getSatScoresForSchool(FAKE_DBN) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get sat score call is made`() = runTest {
        val result = getSatScoresForSchoolUseCase.getSatScoresForSchool(FAKE_DBN)
        //verify no calls happened
        coVerify(exactly = 1) { schoolsRepositoryMock.getSatScoresForSchool(FAKE_DBN) }

        //assertions
        Assert.assertNotNull(result)
        Assert.assertTrue(result is ResultWrapper.Success)

    }
}