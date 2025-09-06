package com.sajjad.feature_events.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.sajjad.core.common.Result
import com.sajjad.feature_events.domain.model.Event
import com.sajjad.feature_events.presentation.EventsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    vm: EventsViewModel,
    onEventClick: (String) -> Unit
) {
    val state by vm.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        vm.startPolling()
    }

    DisposableEffect(Unit) {
        onDispose { vm.stopPolling() }
    }
    val listState = remember { LazyListState() }
    val scope = rememberCoroutineScope()
    val scrolledDown by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("GitHub Events") },
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        },
        floatingActionButton = {
            if (scrolledDown) {
                FloatingActionButton(
                    onClick = { scope.launch { listState.animateScrollToItem(0) } }
                ) { Icon(Icons.Default.ArrowUpward, null) }
            }
        }
    ) { padding ->
        when (state) {
            is Result.Loading -> Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            is Result.Error -> Box(
                Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Failed to load events")
            }

            is Result.Success -> {
                val items = (state as Result.Success<List<Event>>).data
                LazyColumn(
                    state = listState,
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items, key = { it.id }) { event ->
                        EventRow(event) { onEventClick(event.id) }
                        HorizontalDivider()
                    }
                }

                LaunchedEffect(items.size) {
                    val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    if (lastVisible == listState.layoutInfo.totalItemsCount - 1) {
                        vm.loadMore()
                    }
                }
            }
        }
    }
}
@Composable
private fun EventRow(event: Event, onClick: () -> Unit) {
    ListItem(
        headlineContent = {
            Text("${event.type} • ${event.repoName}", maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        supportingContent = { Text(event.actor) },
        leadingContent = {
            Image(
                painter = rememberAsyncImagePainter(event.avatarUrl),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Crop
            )
        },
        overlineContent = { Text(event.createdAt) },
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    val mockEvents = listOf(
        Event(
            id = "1",
            type = "PushEvent",
            actor = "sajjad-dev",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            repoName = "sajjad/sample-repo",
            createdAt = "2025-09-06T12:00:00Z"
        ),
        Event(
            id = "2",
            type = "IssueCommentEvent",
            actor = "john-doe",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            repoName = "johndoe/test-repo",
            createdAt = "2025-09-05T15:30:00Z"
        )
    )

    MaterialTheme {
        EventsScreen(
            state = Result.Success(mockEvents),
            onEventClick = {}
        )
    }
}

