package com.sajjad.feature_events.repository

import com.sajjad.feature_events.common.BaseTest
import com.sajjad.core.common.Result
import com.sajjad.feature_events.data.api.EventsApi
import com.sajjad.feature_events.data.mapper.toDomain
import com.sajjad.feature_events.data.model.ActorDto
import com.sajjad.feature_events.data.model.EventDto
import com.sajjad.feature_events.data.model.RepoDto
import com.sajjad.feature_events.data.repository.EventsRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EventsRepositoryImplTest : BaseTest() {

    @Mock
    private lateinit var api: EventsApi

    private lateinit var repository: EventsRepositoryImpl

    @Before
    fun setupRepository() {
        repository = EventsRepositoryImpl(api)
    }

    @Test
    fun `fetchPage returns Success when api returns allowed events`() = runTest {
        val dto = EventDto(
            id = "1",
            type = "PushEvent",
            actor = ActorDto("sajjad", "avatarUrl"),
            repo = RepoDto("repo1"),
            createdAt = "2025-09-02T10:00:00Z"
        )
        `when`(api.getPublicEvents(any(), any())).thenReturn(listOf(dto))

        val result = repository.fetchPage(1)

        assertTrue(result is Result.Success)
        assertEquals(1, (result).data.size)
        assertEquals(dto.toDomain(), result.data.first())
    }

    @Test
    fun `fetchPage filters out disallowed events`() = runTest {
        val dto = EventDto(
            id = "2",
            type = "SomeOtherEvent",
            actor = ActorDto("sajjad", "avatarUrl"),
            repo = RepoDto("repo2"),
            createdAt = "2025-09-02T10:00:00Z"
        )
        `when`(api.getPublicEvents(any(), any())).thenReturn(listOf(dto))

        val result = repository.fetchPage(1)

        assertTrue(result is Result.Success)
        assertTrue(result.data.isEmpty())
    }

    @Test
    fun `fetchPage returns Error when api throws exception`() = runTest {
        val exception = RuntimeException("Network error")
        `when`(api.getPublicEvents(any(), any())).thenThrow(exception)

        val result = repository.fetchPage(1)

        assertTrue(result is Result.Error)
    }
}
