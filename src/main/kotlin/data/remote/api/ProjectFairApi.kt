package data.remote.api

import data.dto.ProjectResponse
import retrofit2.http.GET

interface ProjectFairApi {
    @GET("projects")
    suspend fun getProjects(): List<ProjectResponse>
}