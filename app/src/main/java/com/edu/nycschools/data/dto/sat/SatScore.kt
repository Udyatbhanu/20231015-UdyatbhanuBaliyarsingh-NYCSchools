package com.edu.nycschools.data.dto.sat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


typealias SatScoresForSchool = List<SatScore>
@JsonClass(generateAdapter = true)
data class SatScore(
    @Json(name = "dbn")
    val dbn: String?,
    @Json(name = "num_of_sat_test_takers")
    val numOfSatTestTakers: String?,
    @Json(name = "sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String?,
    @Json(name = "sat_math_avg_score")
    val satMathAvgScore: String?,
    @Json(name = "sat_writing_avg_score")
    val satWritingAvgScore: String?,
    @Json(name = "school_name")
    val schoolName: String?
)