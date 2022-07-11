package edu.bu.projectportal

import android.app.Activity
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ProjectListDetailTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    val curPos = 1
    val curProj = Project.projects[curPos]

    @Test
    fun testShowRecyclerView() {
        onView(ViewMatchers.withId(R.id.recylReview))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSelectProject() {
        onView(withId(R.id.recylReview))
            .perform(actionOnItemAtPosition<ProjectListRecyclerViewAdapter.ViewHolder>
                (curPos,click()))

        onView(withId(R.id.detailFragment))
            .check(matches(isDisplayed()))

        onView(withId(R.id.projTitle))
            .check(matches(withText(curProj.title)))

        if (InstrumentationRegistry.getInstrumentation().targetContext
                .resources.configuration.orientation == ORIENTATION_PORTRAIT)
                    pressBack()

        onView(withId(R.id.recylReview))
            .check(matches(isDisplayed()))

    }

}