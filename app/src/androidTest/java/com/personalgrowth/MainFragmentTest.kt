package com.personalgrowth

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.personalgrowth.ui.main.MainActivity
import org.junit.Test

class MainFragmentTest {

    @Test
    fun runApp_channelsTitleIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withText(R.string.channels)).check(matches(isDisplayed()))

    }

    @Test
    fun runApp_newEpisodesIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withText(R.string.new_episodes)).check(matches(isDisplayed()))

    }

    @Test
    fun runApp_pullToRefresh() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.contentNsv)).perform(swipeDown())

    }

    @Test
    fun runApp_SwipeUp() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.contentNsv)).perform(swipeUp())

    }

    @Test
    fun runApp_SwipeNewEpisodes() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.contentNsv)).perform(swipeLeft())
        onView(withId(R.id.contentNsv)).perform(swipeRight())

    }

    @Test
    fun runApp_SwipeChannels() {
        ActivityScenario.launch(MainActivity::class.java)
        runApp_SwipeUp()
        onView(withId(R.id.contentNsv)).perform(swipeLeft())
        onView(withId(R.id.contentNsv)).perform(swipeRight())

    }

}