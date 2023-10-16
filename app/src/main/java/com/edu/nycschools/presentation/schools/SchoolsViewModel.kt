package com.edu.nycschools.presentation.schools

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.domain.schools.GetSchoolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolsViewModel @Inject constructor(private val getSchoolsUseCase: GetSchoolsUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<SchoolsFragmentState>(SchoolsFragmentState.Idle)
    val state: StateFlow<SchoolsFragmentState>
        get() = _state

    init {
        fetchSchools()
    }

    fun fetchSchools() {
        viewModelScope.launch {
            _state.value = when (val result = getSchoolsUseCase.getSchools()) {
                is ResultWrapper.Success -> {
                    hideProgressBar()
                    SchoolsFragmentState.Success(result.value)
                }
                else -> {
                    SchoolsFragmentState.Error
                }
            }
        }
    }

    private fun hideProgressBar() {
        viewModelScope.launch { _state.emit(SchoolsFragmentState.HideSpinner) }
    }
}

