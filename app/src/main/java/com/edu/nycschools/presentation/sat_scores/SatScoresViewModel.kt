package com.edu.nycschools.presentation.sat_scores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edu.nycschools.core.ResultWrapper
import com.edu.nycschools.domain.sat_scores.GetSatScoresForSchoolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatScoresViewModel @Inject constructor(private val getSatScoresForSchoolUseCase: GetSatScoresForSchoolUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<SatScoresFragmentState>(SatScoresFragmentState.Idle)
    val state: StateFlow<SatScoresFragmentState>
        get() = _state


    fun getSatScores(dbn: String) {
        viewModelScope.launch {
            _state.value =
                when (val result = getSatScoresForSchoolUseCase.getSatScoresForSchool(dbn)) {
                    is ResultWrapper.Success -> SatScoresFragmentState.Success(result.value)
                    else -> {
                        SatScoresFragmentState.Error
                    }
                }
        }
    }

}