package com.sajjad.feature_events.data.api

import com.sajjad.feature_events.data.model.EventDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsApi {
    @GET("events")
    suspend fun getPublicEvents(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): List<EventDto>
}
