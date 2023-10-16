package com.edu.nycschools.domain.sat_scores

import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.sat.SatScoresForSchool

interface GetSatScoresForSchoolUseCase {
    suspend fun getSatScoresForSchool(dbn : String) : ResultWrapper<SatScoresForSchool>
}