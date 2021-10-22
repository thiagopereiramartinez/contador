package net.nuvem.contador

import android.text.style.LineHeightSpan
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUI() {

        onView(withId(R.id.tvContador))
            .check(
                matches(
                    allOf(
                        isDisplayed(), withText("0")
                    )
                )
            )

        onView(withId(R.id.btnIniciar))
            .check(
                matches(
                    allOf(
                        isDisplayed(), isEnabled(), isClickable(), withText("Iniciar")
                    )
                )
            )

        onView(withId(R.id.btnPausar))
            .check(
                matches(
                    allOf(
                        isDisplayed(), not(isEnabled()), withText("Pausar")
                    )
                )
            )

        onView(withId(R.id.btnResetar))
            .check(
                matches(
                    allOf(
                        isDisplayed(), not(isEnabled()), withText("Resetar")
                    )
                )
            )

    }

    @Test
    fun testIniciarContador() {

        onView(withId(R.id.btnIniciar))
            .perform(click())

        runBlocking {
            delay(3000L)
        }

        onView(withId(R.id.tvContador))
            .check(
                matches(
                    withText("3")
                )
            )

        onView(withId(R.id.btnIniciar))
            .check(
                matches(
                    not(isEnabled())
                )
            )

        onView(withId(R.id.btnPausar))
            .check(
                matches(
                    isEnabled()
                )
            )

        onView(withId(R.id.btnResetar))
            .check(
                matches(
                    isEnabled()
                )
            )

    }

    @Test
    fun testBtnPausar() {

        onView(withId(R.id.btnIniciar))
            .perform(click())

        runBlocking {
            delay(3000L)
        }

        onView(withId(R.id.btnPausar))
            .perform(click())

        onView(withId(R.id.btnIniciar))
            .check(
                matches(
                    isEnabled()
                )
            )

        onView(withId(R.id.btnPausar))
            .check(
                matches(
                    not(isEnabled())
                )
            )

        onView(withId(R.id.btnResetar))
            .check(
                matches(
                    isEnabled()
                )
            )

        runBlocking {
            delay(1000L)
        }

        onView(withId(R.id.tvContador))
            .check(
                matches(
                    withText("3")
                )
            )

    }

    @Test
    fun testBtnReset() {
        onView(withId(R.id.btnIniciar)).perform(click())

        runBlocking {
            delay(3000L)
        }

        onView(withId(R.id.btnResetar)).perform(click())

        onView(withId(R.id.tvContador))
            .check(
                matches(withText("0"))
            )

        onView(withId(R.id.btnIniciar))
            .check(
                matches(
                    isEnabled()
                )
            )

        onView(withId(R.id.btnResetar))
            .check(
                matches(
                    isNotEnabled()
                )
            )
        onView(withId(R.id.btnPausar))
            .check(
                matches(
                    isNotEnabled()
                )
            )
    }
}