package domain.repository

import domain.model.Participation
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.Flow

interface ParticipationRepository {
    fun getParticipations(): Flow<ResultsChange<Participation>>
    suspend fun updateParticipation(participation: Participation)
    suspend fun insertParticipation(participation: Participation)
    suspend fun insertParticipation(participations: List<Participation>)
    suspend fun deleteParticipation(participation: Participation)
    suspend fun deleteAllParticipations()
    suspend fun uploadParticipations()
}