package com.sajjad.feature_events.domain.model

data class Event(
    val id: String,
    val type: String,
    val actor: String,
    val avatarUrl: String,
    val repoName: String,
    val createdAt: String
)
