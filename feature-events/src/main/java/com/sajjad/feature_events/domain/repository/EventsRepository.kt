package com.sajjad.feature_events.domain.repository

import com.sajjad.core.common.Result
import com.sajjad.feature_events.domain.model.Event

interface EventsRepository {
    suspend fun fetchPage(page: Int): Result<List<Event>>
}
