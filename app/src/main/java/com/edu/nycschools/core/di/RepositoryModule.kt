package com.edu.nycschools.core.di

import com.edu.nycschools.data.repository.SchoolsRepository
import com.edu.nycschools.data.repository.SchoolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getGetSchoolsRepository(getSchoolsUseCase: SchoolsRepositoryImpl): SchoolsRepository
}