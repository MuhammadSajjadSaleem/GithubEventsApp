package com.sajjad.githubeventsapp.common

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sajjad.githubeventsapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Base class for all UI integration tests.
 * Sets up Hilt, MockWebServer, and Compose test rule.
 *
 * MainActivity is auto-launched by the Compose rule.
 */
abstract class BaseUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Before
    open fun setUp() {
        hiltRule.inject()
        mockWebServer.start()
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}
