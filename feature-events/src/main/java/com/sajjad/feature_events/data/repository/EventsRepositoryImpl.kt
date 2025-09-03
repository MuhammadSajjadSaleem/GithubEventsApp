package com.sajjad.feature_events.data.repository

import com.sajjad.core.common.Result
import com.sajjad.core.common.safeCall
import com.sajjad.feature_events.data.api.EventsApi
import com.sajjad.feature_events.data.config.EventFilter
import com.sajjad.feature_events.data.mapper.toDomain
import com.sajjad.feature_events.domain.model.Event
import com.sajjad.feature_events.domain.repository.EventsRepository
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val api: EventsApi
) : EventsRepository {

    override suspend fun fetchPage(page: Int): Result<List<Event>> = safeCall {
        api.getPublicEvents(page)
            .filter { it.type in EventFilter.allowedTypes }
            .map { it.toDomain() }
    }
}
