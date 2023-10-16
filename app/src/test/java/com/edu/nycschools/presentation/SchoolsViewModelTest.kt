package com.edu.nycschools.presentation

import com.edu.nycschools.MainDispatcherRule
import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.schools.School
import com.edu.nycschools.domain.schools.GetSchoolsUseCase
import com.edu.nycschools.presentation.schools.SchoolsViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

class SchoolsViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var getSchoolsUseCase: GetSchoolsUseCase

    private lateinit var schoolsViewModel: SchoolsViewModel

    private val fakeSchools = listOf(
        School(
            dbn = "10X439",
            schoolName = "Bronx High School for Law and Community Service",
            overviewParagraph = "Our mission at Bronx High School for Law and Community Service is to support self-motivation, " +
                     "intellectual curiosity, and integrity. Graduates successfully achieve their goals in college, career," +
                     " and beyond. We are a small high school with a four-year college and career plan focused on law" +
                     " enforcement and service to those in need. It is our belief that all people, regardless of their " +
                     "socioeconomic status or ethnic background, can make meaningful contributions to humanity by serving others. " +
                     "With the help of our partners, students are offered meaningful experiences in their chosen area of interest." +
                     "\",\"academicopportunities1\":\"CTE program(s) in: Law and Public Safety",
        )
    )

    @Before
    fun setUp() {
        coEvery { getSchoolsUseCase.getSchools() } returns ResultWrapper.Success(
            fakeSchools
        )
        schoolsViewModel = SchoolsViewModel(getSchoolsUseCase = getSchoolsUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}