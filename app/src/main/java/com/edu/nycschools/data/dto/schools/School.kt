package com.edu.nycschools.data.dto.schools


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


typealias Schools = List<School>

@JsonClass(generateAdapter = true)
data class School(
    @Json(name = "dbn")
    val dbn: String?,
    @Json(name = "school_name")
    val schoolName: String?,
    @Json(name = "overview_paragraph")
    val overviewParagraph: String?,
)