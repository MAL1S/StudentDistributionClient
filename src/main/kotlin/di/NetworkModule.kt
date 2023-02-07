package di

import dagger.Module
import dagger.Provides
import data.remote.api.ProjectFairApi
import data.remote.client.ProjectFairClient

@Module
interface NetworkModule {

    companion object {

        @AppScope
        @Provides
        fun provideProjectFairApi(): ProjectFairApi {
            return ProjectFairClient.getClient()
        }
    }
}