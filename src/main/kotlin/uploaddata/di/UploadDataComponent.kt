package uploaddata.di

import androidx.compose.runtime.Composable
import navigation.NavController
import di.AppComponent
import ru.student.distribution.di.BaseComponent
import uploaddata.UploadDataViewModel
import uploaddata.UploadDataScreen
import javax.inject.Inject

//@Scope
//annotation class UploadDataScope
//
//@[UploadDataScope Component(
//    dependencies = [
//        AppComponent::class
//    ],
//    modules = [
//        UploadDataViewModelModule::class
//    ]
//)]
//interface UploadDataComponent {
//
//    @Component.Factory
//    interface Factory {
//
//        fun create(): UploadDataComponent
//    }
//}

//@Module
//interface UploadDataViewModelModule {
//
//    companion object {
//
//        @UploadDataScope
//        @Provides
//        fun provideUploadDataViewModel(syncDataUseCase: SyncDataUseCase): UploadDataViewModel {
//            return UploadDataViewModel(syncDataUseCase)
//        }
//    }
//}

class UploadDataComponent(
    navController: NavController,
    appComponent: AppComponent
) : BaseComponent {

    @Inject
    lateinit var uploadDataViewModel: UploadDataViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        UploadDataScreen(uploadDataViewModel)
    }
}