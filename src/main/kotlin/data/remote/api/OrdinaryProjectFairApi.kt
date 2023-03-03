package data.remote.api

import data.dto.InstituteResponse
import data.dto.StudentResponse
import retrofit2.http.GET

interface OrdinaryProjectFairApi {
    @GET("institutes")
    suspend fun getInstitutes(): List<InstituteResponse>

    @GET("candidates")
    suspend fun getCandidates(): List<StudentResponse>
}