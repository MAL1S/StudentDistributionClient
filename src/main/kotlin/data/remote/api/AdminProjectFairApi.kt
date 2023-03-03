package data.remote.api

import data.dto.ParticipationResponse
import data.dto.ProjectResponse
import retrofit2.http.GET

interface AdminProjectFairApi {

    @GET("projects")
    suspend fun getProjects(): List<ProjectResponse>

    @GET("participations")
    suspend fun getParticipations(): List<ParticipationResponse>
}