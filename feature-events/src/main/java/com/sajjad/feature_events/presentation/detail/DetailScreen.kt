package com.sajjad.feature_events.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sajjad.feature_events.domain.model.Event

@Composable
fun EventDetailScreen(event: Event) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(event.type, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Repository: ${event.repoName}")
        Text("Actor: ${event.actor}")
        Spacer(Modifier.height(8.dp))
        AsyncImage(
            model = event.avatarUrl,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text("Created: ${event.createdAt}")
    }
}
