package ru.student.distribution.di

import dagger.Component
import ui.uploaddata.di.UploadDataComponent
import javax.inject.Scope

@Scope
annotation class AppScope

@[AppScope Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        CoroutineDispatcherModule::class
    ]
)]
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }

    fun inject(uploadDataComponent: UploadDataComponent)
}