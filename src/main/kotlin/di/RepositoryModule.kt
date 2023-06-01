package di

import dagger.Module
import dagger.Provides
import data.local.dao.*
import data.remote.api.AdminProjectFairApi
import data.remote.api.OrdinaryProjectFairApi
import data.repository.*
import domain.repository.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.math.log

@Module
interface RepositoryModule {

    companion object {

        @AppScope
        @Provides
        fun provideLoggingRepository(
            ioDispatcher: CoroutineDispatcher,
            logDao: LogDao,
        ): LoggingRepository {
            return LoggingRepositoryImpl(
                ioDispatcher = ioDispatcher,
                logDao = logDao
            )
        }

        @Preview
        @AppScope
        @Provides
        fun providePreviewUploadDataRepository(
            ioDispatcher: CoroutineDispatcher,
            @Preview studentRepository: StudentRepository,
            @Preview projectRepository: ProjectRepository,
            @Preview participationRepository: ParticipationRepository,
            instituteRepository: InstituteRepository,
            departmentRepository: DepartmentRepository,
            supervisorRepository: SupervisorRepository,
            loggingRepository: LoggingRepository
        ): UploadDataRepository {
            return UploadDataRepositoryImpl(
                ioDispatcher = ioDispatcher,
                studentRepository = studentRepository,
                projectRepository = projectRepository,
                participationRepository = participationRepository,
                instituteRepository = instituteRepository,
                departmentRepository = departmentRepository,
                supervisorRepository = supervisorRepository,
                loggingRepository = loggingRepository
            )
        }

        @Review
        @AppScope
        @Provides
        fun provideReviewUploadDataRepository(
            ioDispatcher: CoroutineDispatcher,
            @Review studentRepository: StudentRepository,
            @Review projectRepository: ProjectRepository,
            @Review participationRepository: ParticipationRepository,
            instituteRepository: InstituteRepository,
            departmentRepository: DepartmentRepository,
            supervisorRepository: SupervisorRepository,
            loggingRepository: LoggingRepository
        ): UploadDataRepository {
            return UploadDataRepositoryImpl(
                ioDispatcher = ioDispatcher,
                studentRepository = studentRepository,
                projectRepository = projectRepository,
                participationRepository = participationRepository,
                instituteRepository = instituteRepository,
                departmentRepository = departmentRepository,
                supervisorRepository = supervisorRepository,
                loggingRepository = loggingRepository
            )
        }

        @Preview
        @AppScope
        @Provides
        fun provideStudentPreviewRepository(
            ioDispatcher: CoroutineDispatcher,
            @Preview studentDao: StudentDao,
            projectFairApi: OrdinaryProjectFairApi,
            loggingRepository: LoggingRepository
        ): StudentRepository {
            return StudentRepositoryImpl(
                ioDispatcher = ioDispatcher,
                studentDao = studentDao,
                projectFairApi = projectFairApi,
                loggingRepository = loggingRepository
            )
        }

        @Review
        @AppScope
        @Provides
        fun provideStudentReviewRepository(
            ioDispatcher: CoroutineDispatcher,
            @Review studentDao: StudentDao,
            projectFairApi: OrdinaryProjectFairApi,
            loggingRepository: LoggingRepository
        ): StudentRepository {
            return StudentRepositoryImpl(
                ioDispatcher = ioDispatcher,
                studentDao = studentDao,
                projectFairApi = projectFairApi,
                loggingRepository = loggingRepository
            )
        }

        @Preview
        @AppScope
        @Provides
        fun providePreviewProjectRepository(
            ioDispatcher: CoroutineDispatcher,
            @Preview projectDao: ProjectDao,
            projectFairApi: OrdinaryProjectFairApi,
            loggingRepository: LoggingRepository
        ): ProjectRepository {
            return ProjectRepositoryImpl(
                ioDispatcher = ioDispatcher,
                projectDao = projectDao,
                projectFairApi = projectFairApi,
                loggingRepository = loggingRepository
            )
        }

        @Review
        @AppScope
        @Provides
        fun provideReviewProjectRepository(
            ioDispatcher: CoroutineDispatcher,
            @Review projectDao: ProjectDao,
            projectFairApi: OrdinaryProjectFairApi,
            loggingRepository: LoggingRepository
        ): ProjectRepository {
            return ProjectRepositoryImpl(
                ioDispatcher = ioDispatcher,
                projectDao = projectDao,
                projectFairApi = projectFairApi,
                loggingRepository = loggingRepository
            )
        }

        @Preview
        @AppScope
        @Provides
        fun providePreviewParticipationRepository(
            ioDispatcher: CoroutineDispatcher,
            @Preview participationDao: ParticipationDao,
            ordinaryProjectFairApi: OrdinaryProjectFairApi,
            loggingRepository: LoggingRepository
        ): ParticipationRepository {
            return ParticipationRepositoryImpl(
                ioDispatcher = ioDispatcher,
                participationDao = participationDao,
                ordinaryProjectFairApi = ordinaryProjectFairApi,
                loggingRepository = loggingRepository
            )
        }

        @Review
        @AppScope
        @Provides
        fun provideReviewParticipationRepository(
            ioDispatcher: CoroutineDispatcher,
            @Review participationDao: ParticipationDao,
            ordinaryProjectFairApi: OrdinaryProjectFairApi,
            loggingRepository: LoggingRepository
        ): ParticipationRepository {
            return ParticipationRepositoryImpl(
                ioDispatcher = ioDispatcher,
                participationDao = participationDao,
                ordinaryProjectFairApi = ordinaryProjectFairApi,
                loggingRepository = loggingRepository
            )
        }

        @AppScope
        @Provides
        fun provideInstituteRepository(
            ioDispatcher: CoroutineDispatcher,
            instituteDao: InstituteDao,
            projectFairApi: OrdinaryProjectFairApi
        ): InstituteRepository {
            return InstituteRepositoryImpl(
                ioDispatcher = ioDispatcher,
                instituteDao = instituteDao,
                projectFairApi = projectFairApi
            )
        }

        @AppScope
        @Provides
        fun provideDepartmentRepository(
            ioDispatcher: CoroutineDispatcher,
            departmentDao: DepartmentDao,
            projectFairApi: OrdinaryProjectFairApi,
            adminProjectFairApi: AdminProjectFairApi
        ): DepartmentRepository {
            return DepartmentRepositoryImpl(
                ioDispatcher = ioDispatcher,
                departmentDao = departmentDao,
                projectFairApi = projectFairApi,
                adminProjectFairApi = adminProjectFairApi
            )
        }

        @AppScope
        @Provides
        fun provideSpecialtyRepository(
            ioDispatcher: CoroutineDispatcher,
            specialtyDao: SpecialtyDao,
            projectFairApi: OrdinaryProjectFairApi
        ): SpecialtyRepository {
            return SpecialtyRepositoryImpl(
                ioDispatcher = ioDispatcher,
                specialtyDao = specialtyDao,
                projectFairApi = projectFairApi
            )
        }

        @AppScope
        @Provides
        fun provideSupervisorRepository(
            ioDispatcher: CoroutineDispatcher,
            supervisorDao: SupervisorDao,
            projectFairApi: OrdinaryProjectFairApi
        ): SupervisorRepository {
            return SupervisorRepositoryImpl(
                ioDispatcher = ioDispatcher,
                supervisorDao = supervisorDao,
                projectFairApi = projectFairApi
            )
        }
    }
}