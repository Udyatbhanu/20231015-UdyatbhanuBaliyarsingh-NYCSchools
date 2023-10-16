package com.edu.nycschools.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.edu.nycschools.core.BaseRepository
import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.core.network.SchoolsApi
import com.edu.nycschools.data.dto.sat.SatScoresForSchool
import com.edu.nycschools.data.dto.schools.Schools
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class SchoolsRepositoryImpl @Inject constructor(private val schoolsApi: SchoolsApi) :
    SchoolsRepository, BaseRepository() {
    override suspend fun getSchools(): ResultWrapper<Schools> =
        invoke {
            schoolsApi.getSchools()
        }

    override suspend fun getSatScoresForSchool(dbn: String): ResultWrapper<SatScoresForSchool> =
        invoke {
            schoolsApi.getSatScores(dbn)
        }

}