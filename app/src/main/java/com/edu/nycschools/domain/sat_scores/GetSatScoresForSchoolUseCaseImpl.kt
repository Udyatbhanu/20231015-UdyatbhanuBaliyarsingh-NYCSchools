package com.edu.nycschools.domain.sat_scores

import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.sat.SatScoresForSchool
import com.edu.nycschools.data.repository.SchoolsRepository
import javax.inject.Inject

class GetSatScoresForSchoolUseCaseImpl @Inject constructor(private val schoolsRepository: SchoolsRepository) :
    GetSatScoresForSchoolUseCase {
    override suspend fun getSatScoresForSchool(dbn : String): ResultWrapper<SatScoresForSchool> {
        return when (val result = schoolsRepository.getSatScoresForSchool(dbn)) {
            is ResultWrapper.Success -> ResultWrapper.Success(result.value)
            is ResultWrapper.GenericError -> ResultWrapper.GenericError()
            else -> ResultWrapper.EmptyResponse
        }
    }
}