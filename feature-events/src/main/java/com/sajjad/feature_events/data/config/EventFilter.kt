package com.sajjad.feature_events.data.config

object EventFilter {
    val allowedTypes = setOf(
        "PushEvent",
        "WatchEvent",
        "ForkEvent",
        "IssuesEvent",
        "PullRequestEvent"
    )
}
