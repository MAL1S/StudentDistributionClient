package uploaddata

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.Bundle
import navigation.NavController
import navigation.SharedScreen
import ru.student.distribution.ui.uploaddata.UploadFileCard

@Composable
fun UploadDataScreen(
    navController: NavController,
    uploadDataViewModel: UploadDataViewModel,
    id: Int,
) {
    println(id)
    when (val screenState = uploadDataViewModel.uiState.collectAsState().value) {
        is UploadDataContract.ScreenState.Idle -> {
            println("SCREEN IDLE")
            uploadDataViewModel.setIntent(
                intent = UploadDataContract.Intent.SyncData
            )
        }

        is UploadDataContract.ScreenState.Loading -> {
            println("SCREEN LOADING")
        }

        is UploadDataContract.ScreenState.SideEffect -> {
            SideEffectHandler(
                effectState = screenState.sideEffect
            )
        }

        is UploadDataContract.ScreenState.Data -> {
            UploadDataStateHandler(
                navController = navController,
                uploadDataState = screenState.uploadDataState,
                uploadDataViewModel = uploadDataViewModel
            )
        }
    }
}

@Composable
fun UploadDataScreenView(
    navController: NavController,
    uploadDataViewModel: UploadDataViewModel,
) {
    //uploadDataViewModel.setIntent(UploadDataContract.Intent.SyncData)
    Box {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            UploadFileCard("Students") {
                navController.navigate(SharedScreen.DetailsScreen.screenRoute, Bundle())
            }
            UploadFileCard("Teachers", {})
            //UpdateDataButton()
        }
    }
}

@Composable
fun SideEffectHandler(effectState: UploadDataContract.SideEffect) {
    when (effectState) {
        is UploadDataContract.SideEffect.ShowError -> {
            println("ERROR SIDE EFFECT")
        }
    }
}

@Composable
fun UploadDataStateHandler(
    navController: NavController,
    uploadDataState: UploadDataContract.UploadDataState,
    uploadDataViewModel: UploadDataViewModel,
) {
    when (uploadDataState) {
        is UploadDataContract.UploadDataState.Loading -> {
            println("LOADING")
        }

        is UploadDataContract.UploadDataState.Error -> {
            println("ERROR")
        }

        is UploadDataContract.UploadDataState.Success -> {
            println("SUCCESS")
            UploadDataScreenView(navController, uploadDataViewModel)
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    Button(
        content = {
            Text("ADAFSDFSDFSD")
        },
        onClick = {
            navController.navigateBack()
        }
    )
}