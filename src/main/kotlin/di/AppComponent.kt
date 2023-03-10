package di

import dagger.Component
import ui.details.project.di.ProjectDetailsComponent
import ui.preview.di.PreviewComponent
import ui.uploaddata.di.UploadDataComponent
import javax.inject.Scope

@Scope
annotation class AppScope

@[AppScope Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        CoroutineDispatcherModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        InteractorModule::class
    ]
)]
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(): AppComponent
    }

    fun inject(uploadDataComponent: UploadDataComponent)
    fun inject(previewComponent: PreviewComponent)
    fun inject(projectDetailsComponent: ProjectDetailsComponent)
}