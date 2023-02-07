package data.remote.client

import data.remote.api.ProjectFairApi

object ProjectFairClient {

    @Synchronized
    fun getClient(): ProjectFairApi = BaseClient.getRetrofitClient().create(ProjectFairApi::class.java)
}