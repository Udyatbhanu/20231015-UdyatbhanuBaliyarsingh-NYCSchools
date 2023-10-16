package com.edu.nycschools.core.network

import com.edu.nycschools.data.dto.sat.SatScoresForSchool
import com.edu.nycschools.data.dto.schools.Schools
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsApi {

    @GET(Endpoints.NYC_SCHOOLS)
    suspend fun getSchools(): Schools

    @GET(Endpoints.SAT_SCORES)
    suspend fun getSatScores(@Query("dbn") lat: String): SatScoresForSchool
}