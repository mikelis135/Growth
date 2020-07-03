package com.mindvalley.personalgrowth

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test

class MainFragmentTest {

    @Test
    fun runApp(){
        ActivityScenario.launch(MainActivity::class.java)

        onView(withText("ok")).check(matches(isDisplayed()))

    }
}