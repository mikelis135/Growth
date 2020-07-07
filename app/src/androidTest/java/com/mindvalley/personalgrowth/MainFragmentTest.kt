package com.mindvalley.personalgrowth

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.mindvalley.personalgrowth.ui.main.MainActivity
import org.junit.Test

class MainFragmentTest {

    @Test
    fun runApp_channelsTitleIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withText("Channels")).check(matches(isDisplayed()))

    }

}