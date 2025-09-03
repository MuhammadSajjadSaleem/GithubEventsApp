package com.sajjad.feature_events.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    val id: String,
    val type: String,
    val actor: ActorDto,
    val repo: RepoDto,
    @Json(name = "created_at") val createdAt: String
)

@JsonClass(generateAdapter = true)
data class ActorDto(
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String
)

@JsonClass(generateAdapter = true)
data class RepoDto(
    val name: String
)
