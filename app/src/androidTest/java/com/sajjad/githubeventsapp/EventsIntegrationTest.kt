package com.sajjad.githubeventsapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sajjad.githubeventsapp.common.BaseUiTest
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Test

@HiltAndroidTest
class EventsIntegrationTest : BaseUiTest() {
    @Test
    fun eventsList_and_navigateToDetail() {
        val mockResponse = """
        [
          {
            "id": "1",
            "type": "PushEvent",
            "actor": { "login": "sajjad", "avatar_url": "https://example.com/avatar.png" },
            "repo": { "name": "repo1" },
            "created_at": "2025-09-02T10:00:00Z"
          }
        ]
    """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        composeRule.waitUntil(
            condition = {
                composeRule.onAllNodes(hasText("PushEvent • repo1"))
                    .fetchSemanticsNodes().isNotEmpty()
            },
            timeoutMillis = 5_000
        )

        composeRule.onNodeWithText("PushEvent • repo1").assertIsDisplayed()
        composeRule.onNodeWithText("PushEvent • repo1").performClick()
        composeRule.onNodeWithText("PushEvent").assertIsDisplayed()

    }


}
