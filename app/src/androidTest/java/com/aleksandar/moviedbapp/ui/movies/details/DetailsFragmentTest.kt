package com.aleksandar.moviedbapp.ui.movies.details

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.MainActivity
import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailsFragmentTest : TestCase() {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun buttonIsEnabled() {
        onView(withId(R.id.imageViewFavourite)).check(matches(isEnabled()))
    }

    @Test fun buttonIsDisplayed() {
        onView(withId(R.id.imageViewFavourite)).check(matches(isDisplayed()))
    }

    @Test fun buttonIsCompletelyDisplayed() {
        onView(withId(R.id.imageViewFavourite)).check(matches(isCompletelyDisplayed()))
    }

    @Test fun buttonIsNotSelectable() {
        onView(withId(R.id.imageViewFavourite)).check(matches(CoreMatchers.not(isSelected())))
    }

    @Test fun buttonIsClickable() {
        onView(withId(R.id.imageViewFavourite)).check(matches(CoreMatchers.not(isClickable())))
    }
}