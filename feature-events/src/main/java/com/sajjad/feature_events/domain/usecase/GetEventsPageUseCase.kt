package com.sajjad.feature_events.domain.usecase

import com.sajjad.core.common.Result
import com.sajjad.feature_events.data.repository.EventsRepositoryImpl
import com.sajjad.feature_events.domain.model.Event
import javax.inject.Inject

class GetEventsPageUseCase @Inject constructor(
    private val repo: EventsRepositoryImpl
) {
    suspend operator fun invoke(page: Int): Result<List<Event>> {
        return repo.fetchPage(page)
    }
}
