package com.sajjad.feature_events.domain.usecase

import com.sajjad.core.common.Result
import com.sajjad.feature_events.data.repository.EventsRepositoryImpl
import com.sajjad.feature_events.domain.model.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PollEventsUseCase @Inject constructor(
    private val repo: EventsRepositoryImpl
) {
    operator fun invoke(intervalMs: Long = 10_000): Flow<Result<List<Event>>> = flow {
        while (true) {
            emit(repo.fetchPage(1))
            delay(intervalMs)
        }
    }
}
