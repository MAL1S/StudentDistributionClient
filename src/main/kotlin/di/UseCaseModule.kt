package ru.student.distribution.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import ru.student.distribution.data.repository.UploadDataRepositoryImpl
import ru.student.distribution.domain.repository.UploadDataRepository
import ru.student.distribution.domain.usecase.uploaddata.SyncDataUseCase

@Module
interface UseCaseModule {

    companion object {

        @AppScope
        @Provides
        fun provideSyncDataUseCase(uploadDataRepository: UploadDataRepository): SyncDataUseCase {
            return SyncDataUseCase(
                uploadDataRepository = uploadDataRepository
            )
        }
    }
}