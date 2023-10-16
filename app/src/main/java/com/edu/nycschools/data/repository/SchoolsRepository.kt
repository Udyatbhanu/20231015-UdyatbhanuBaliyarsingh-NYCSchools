package com.edu.nycschools.data.repository

import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.sat.SatScoresForSchool
import com.edu.nycschools.data.dto.schools.Schools

interface SchoolsRepository{

    /**
     * Get Schools
     */
    suspend fun getSchools() : ResultWrapper<Schools>

    /**
     * Get Sat scores for school
     */
    suspend fun getSatScoresForSchool(dbn : String) : ResultWrapper<SatScoresForSchool>
}