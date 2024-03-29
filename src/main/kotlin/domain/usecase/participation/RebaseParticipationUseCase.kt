package domain.usecase.participation

import domain.repository.ParticipationRepository
import javax.inject.Inject

class RebaseParticipationUseCase(
    private val participationRepository: ParticipationRepository
) {

    suspend operator fun invoke() {
        participationRepository.rebaseParticipations()
    }
}