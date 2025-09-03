package com.sajjad.feature_events.data.mapper

import com.sajjad.feature_events.data.model.EventDto
import com.sajjad.feature_events.domain.model.Event

fun EventDto.toDomain() = Event(
    id = id,
    type = type,
    actor = actor.login,
    avatarUrl = actor.avatarUrl,
    repoName = repo.name,
    createdAt = createdAt
)
