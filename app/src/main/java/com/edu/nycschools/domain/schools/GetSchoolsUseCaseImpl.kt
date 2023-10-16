package com.edu.nycschools.domain.schools

import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.data.dto.schools.Schools
import com.edu.nycschools.data.repository.SchoolsRepository
import javax.inject.Inject

class GetSchoolsUseCaseImpl @Inject constructor(private val schoolsRepository: SchoolsRepository) :
    GetSchoolsUseCase {
    override suspend fun getSchools(): ResultWrapper<Schools> {
        return when (val result = schoolsRepository.getSchools()) {
            is ResultWrapper.Success -> ResultWrapper.Success(result.value)
            is ResultWrapper.GenericError -> ResultWrapper.GenericError()
            else -> ResultWrapper.EmptyResponse
        }
    }
}