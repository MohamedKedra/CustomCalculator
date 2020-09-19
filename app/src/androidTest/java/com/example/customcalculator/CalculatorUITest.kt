package com.example.customcalculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.customcalculator.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CalculatorUITest {

    @Rule @JvmField
    val activityTestRule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun oneOperation(){

        Espresso.onView(withId(R.id.btn_add)).perform(click())
        Espresso.onView(withId(R.id.et_operand)).perform(typeText("4"))
        Espresso.onView(withId(R.id.btn_equal)).perform(click())
        Espresso.onView(withId(R.id.tv_result)).check(matches(withText("Result =4")))
    }

    @Test
    fun twoOperation(){

        Espresso.onView(withId(R.id.btn_add)).perform(click())
        Espresso.onView(withId(R.id.et_operand)).perform(typeText("9"))
        Espresso.onView(withId(R.id.btn_equal)).perform(click())
        Espresso.onView(withId(R.id.tv_result)).check(matches(withText("Result =9")))

        Espresso.onView(withId(R.id.btn_div)).perform(click())
        Espresso.onView(withId(R.id.et_operand)).perform(typeText("3"))
        Espresso.onView(withId(R.id.btn_equal)).perform(click())
        Espresso.onView(withId(R.id.tv_result)).check(matches(withText("Result =3")))
    }
}