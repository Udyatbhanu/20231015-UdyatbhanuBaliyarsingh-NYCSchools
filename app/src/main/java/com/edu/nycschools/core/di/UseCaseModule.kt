package com.edu.nycschools.core.di

import com.edu.nycschools.domain.sat_scores.GetSatScoresForSchoolUseCase
import com.edu.nycschools.domain.sat_scores.GetSatScoresForSchoolUseCaseImpl
import com.edu.nycschools.domain.schools.GetSchoolsUseCase
import com.edu.nycschools.domain.schools.GetSchoolsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class  UseCaseModule {

    @Binds
    abstract fun getGetSchoolsUseCase(getSchoolsUseCase: GetSchoolsUseCaseImpl): GetSchoolsUseCase

    @Binds
    abstract fun GetSatScoresForSchoolUseCase(getSchoolsUseCase: GetSatScoresForSchoolUseCaseImpl): GetSatScoresForSchoolUseCase
}