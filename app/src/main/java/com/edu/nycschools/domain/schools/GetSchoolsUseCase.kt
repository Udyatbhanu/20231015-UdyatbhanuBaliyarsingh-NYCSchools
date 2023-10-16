package com.edu.nycschools.domain.schools

import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.schools.Schools

interface GetSchoolsUseCase {

    /**
     * Get schools use case.
     */
    suspend fun getSchools() : ResultWrapper<Schools>
}