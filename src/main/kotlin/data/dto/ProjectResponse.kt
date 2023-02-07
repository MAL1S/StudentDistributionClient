package data.dto

import com.google.gson.annotations.SerializedName

data class ProjectResponse(
    val id: Int,
    val title: String,
    val places: Int,
    val goal: String?,
    val difficulty: Int,
    @SerializedName("date_start") val dateStart: String,
    @SerializedName("date_end") val dateEnd: String,
    val customer: String?,
    @SerializedName("product_result") val productResult: String,
    @SerializedName("study_result") val studyResult: String,
    @SerializedName("supervisorsNames") val supervisors: String,
    val stateResponse: StateResponse,
    val specialities: List<SpecialtyResponse>
)