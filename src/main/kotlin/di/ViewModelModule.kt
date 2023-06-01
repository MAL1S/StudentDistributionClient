package di

import dagger.Module
import dagger.Provides
import domain.interactor.DownloadProgressInteractor
import domain.usecase.department.GetDepartmentsUseCase
import domain.usecase.department.UploadDepartmentsUseCase
import domain.usecase.institute.GetInstitutesUseCase
import domain.usecase.institute.UploadInstitutesUseCase
import domain.usecase.logging.GetLogsUseCase
import domain.usecase.logging.SaveLogUseCase
import domain.usecase.participation.GetParticipationsUseCase
import domain.usecase.participation.RebaseParticipationUseCase
import domain.usecase.participation.SyncParticipationUseCase
import domain.usecase.project.*
import domain.usecase.specialty.GetSpecialtiesUseCase
import domain.usecase.student.GetStudentsUseCase
import domain.usecase.student.RebaseStudentsUseCase
import domain.usecase.student.SyncStudentsUseCase
import domain.usecase.supervisor.GetSupervisorsUseCase
import domain.usecase.supervisor.UploadSupervisorsUseCase
import domain.usecase.uploaddata.CancelOperationsUseCase
import domain.usecase.uploaddata.RebaseDataUseCase
import domain.usecase.uploaddata.SyncDataUseCase
import ui.details.participation.viewmodel.ParticipationDetailsViewModel
import ui.distribution_algorithm.viewmodel.AlgorithmViewModel
import ui.preview.viewmodel.PreviewViewModel
import ui.review.viewmodel.ReviewViewModel
import ui.uploaddata.viewmodel.UploadDataViewModel

@Module
interface ViewModelModule {

    companion object {

        @Preview
        @AppScope
        @Provides
        fun providePreviewUploadDataViewModel(
            @Preview syncDataUseCase: SyncDataUseCase,
            @Preview rebaseDataUseCase: RebaseDataUseCase,
            @Preview syncStudentsUseCase: SyncStudentsUseCase,
            @Preview rebaseStudentsUseCase: RebaseStudentsUseCase,
            @Preview syncProjectsUseCase: SyncProjectsUseCase,
            @Preview rebaseProjectsUseCase: RebaseProjectsUseCase,
            @Preview syncParticipationUseCase: SyncParticipationUseCase,
            @Preview rebaseParticipationUseCase: RebaseParticipationUseCase,
            uploadSupervisorsUseCase: UploadSupervisorsUseCase,
            uploadDepartmentsUseCase: UploadDepartmentsUseCase,
            uploadInstitutesUseCase: UploadInstitutesUseCase,
            @Preview cancelOperationsUseCase: CancelOperationsUseCase,
            @Preview downloadProgressInteractor: DownloadProgressInteractor,
        ): UploadDataViewModel {
            return UploadDataViewModel(
                syncDataUseCase = syncDataUseCase,
                rebaseDataUseCase = rebaseDataUseCase,
                syncStudentsUseCase = syncStudentsUseCase,
                rebaseStudentsUseCase = rebaseStudentsUseCase,
                syncProjectsUseCase = syncProjectsUseCase,
                rebaseProjectsUseCase = rebaseProjectsUseCase,
                syncParticipationUseCase = syncParticipationUseCase,
                rebaseParticipationUseCase = rebaseParticipationUseCase,
                uploadSupervisorsUseCase = uploadSupervisorsUseCase,
                uploadDepartmentsUseCase = uploadDepartmentsUseCase,
                uploadInstitutesUseCase = uploadInstitutesUseCase,
                cancelOperationsUseCase = cancelOperationsUseCase,
                downloadProgressInteractor = downloadProgressInteractor
            )
        }

        @Review
        @AppScope
        @Provides
        fun provideReviewUploadDataViewModel(
            @Review syncDataUseCase: SyncDataUseCase,
            @Review rebaseDataUseCase: RebaseDataUseCase,
            @Review syncStudentsUseCase: SyncStudentsUseCase,
            @Review rebaseStudentsUseCase: RebaseStudentsUseCase,
            @Review syncProjectsUseCase: SyncProjectsUseCase,
            @Review rebaseProjectsUseCase: RebaseProjectsUseCase,
            @Review syncParticipationUseCase: SyncParticipationUseCase,
            @Review rebaseParticipationUseCase: RebaseParticipationUseCase,
            uploadSupervisorsUseCase: UploadSupervisorsUseCase,
            uploadDepartmentsUseCase: UploadDepartmentsUseCase,
            uploadInstitutesUseCase: UploadInstitutesUseCase,
            @Review cancelOperationsUseCase: CancelOperationsUseCase,
            @Review downloadProgressInteractor: DownloadProgressInteractor,
        ): UploadDataViewModel {
            return UploadDataViewModel(
                syncDataUseCase = syncDataUseCase,
                rebaseDataUseCase = rebaseDataUseCase,
                syncStudentsUseCase = syncStudentsUseCase,
                rebaseStudentsUseCase = rebaseStudentsUseCase,
                syncProjectsUseCase = syncProjectsUseCase,
                rebaseProjectsUseCase = rebaseProjectsUseCase,
                syncParticipationUseCase = syncParticipationUseCase,
                rebaseParticipationUseCase = rebaseParticipationUseCase,
                uploadSupervisorsUseCase = uploadSupervisorsUseCase,
                uploadDepartmentsUseCase = uploadDepartmentsUseCase,
                uploadInstitutesUseCase = uploadInstitutesUseCase,
                cancelOperationsUseCase = cancelOperationsUseCase,
                downloadProgressInteractor = downloadProgressInteractor
            )
        }

        @AppScope
        @Provides
        fun providePreviewViewModel(
            @Preview getStudentsUseCase: GetStudentsUseCase,
            @Preview getProjectsUseCase: GetProjectsUseCase,
            @Preview updateProjectUseCase: UpdateProjectUseCase,
            @Preview getParticipationsUseCase: GetParticipationsUseCase,
            getInstitutesUseCase: GetInstitutesUseCase,
            getDepartmentsUseCase: GetDepartmentsUseCase,
            getSpecialtiesUseCase: GetSpecialtiesUseCase,
            getSupervisorsUseCase: GetSupervisorsUseCase,
            @Preview syncProjectUseCase: SyncProjectUseCase,
            getLogsUseCase: GetLogsUseCase,
            saveLogUseCase: SaveLogUseCase
        ): PreviewViewModel {
            return PreviewViewModel(
                getStudentsUseCase = getStudentsUseCase,
                getProjectsUseCase = getProjectsUseCase,
                updateProjectUseCase = updateProjectUseCase,
                getParticipationsUseCase = getParticipationsUseCase,
                getInstitutesUseCase = getInstitutesUseCase,
                getDepartmentsUseCase = getDepartmentsUseCase,
                getSpecialtiesUseCase = getSpecialtiesUseCase,
                getSupervisorsUseCase = getSupervisorsUseCase,
                syncProjectUseCase = syncProjectUseCase,
                getLogsUseCase = getLogsUseCase,
                saveLogUseCase = saveLogUseCase
            )
        }

        @AppScope
        @Provides
        fun provideReviewViewModel(
            @Review getStudentsUseCase: GetStudentsUseCase,
            @Review getProjectsUseCase: GetProjectsUseCase,
            @Review updateProjectUseCase: UpdateProjectUseCase,
            @Review getParticipationsUseCase: GetParticipationsUseCase,
            getInstitutesUseCase: GetInstitutesUseCase,
            getDepartmentsUseCase: GetDepartmentsUseCase,
            getSpecialtiesUseCase: GetSpecialtiesUseCase,
            getSupervisorsUseCase: GetSupervisorsUseCase,
            @Review syncProjectUseCase: SyncProjectUseCase,
            getLogsUseCase: GetLogsUseCase,
            saveLogUseCase: SaveLogUseCase,
        ): ReviewViewModel {
            return ReviewViewModel(
                getStudentsUseCase = getStudentsUseCase,
                getProjectsUseCase = getProjectsUseCase,
                updateProjectUseCase = updateProjectUseCase,
                getParticipationsUseCase = getParticipationsUseCase,
                getInstitutesUseCase = getInstitutesUseCase,
                getDepartmentsUseCase = getDepartmentsUseCase,
                getSpecialtiesUseCase = getSpecialtiesUseCase,
                getSupervisorsUseCase = getSupervisorsUseCase,
                syncProjectUseCase = syncProjectUseCase,
                getLogsUseCase = getLogsUseCase,
                saveLogUseCase = saveLogUseCase,
            )
        }

        @AppScope
        @Provides
        fun provideAlgorithmViewModel(
            @Preview getStudentsUseCase: GetStudentsUseCase,
            @Preview getProjectsUseCase: GetProjectsUseCase,
            @Preview getParticipationsUseCase: GetParticipationsUseCase,
            getInstitutesUseCase: GetInstitutesUseCase,
        ): AlgorithmViewModel {
            return AlgorithmViewModel(
                getStudentsUseCase = getStudentsUseCase,
                getProjectsUseCase = getProjectsUseCase,
                getParticipationsUseCase = getParticipationsUseCase,
                getInstitutesUseCase = getInstitutesUseCase,
            )
        }

        @Preview
        @Provides
        fun providePreviewParticipationDetailsViewModel(
            @Preview getParticipationsUseCase: GetParticipationsUseCase,
            @Preview getStudentsUseCase: GetStudentsUseCase
        ): ParticipationDetailsViewModel {
            return ParticipationDetailsViewModel(
                getParticipationsUseCase = getParticipationsUseCase,
                getStudentsUseCase = getStudentsUseCase
            )
        }

        @Review
        @Provides
        fun provideReviewParticipationDetailsViewModel(
            @Review getParticipationsUseCase: GetParticipationsUseCase,
            @Review getStudentsUseCase: GetStudentsUseCase
        ): ParticipationDetailsViewModel {
            return ParticipationDetailsViewModel(
                getParticipationsUseCase = getParticipationsUseCase,
                getStudentsUseCase = getStudentsUseCase
            )
        }
    }
}