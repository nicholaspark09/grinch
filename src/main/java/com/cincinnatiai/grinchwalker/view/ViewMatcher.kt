package com.cincinnatiai.grinchwalker.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry

interface ViewMatcherContract {

    fun idHasText(layoutId: Int, @StringRes textResource: Int)

    fun idHasImage(layoutId: Int, @DrawableRes drawableRes: Int)
}

class ViewMatcher private constructor(val context: Context) : ViewMatcherContract {

    override fun idHasImage(layoutId: Int, drawableRes: Int) {
    }

    override fun idHasText(layoutId: Int, textResource: Int) {
        onView(withId(layoutId)).check(matches(withText(context.getString(textResource))))
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewMatcher? = null

        @JvmStatic
        fun initialize(context: Context = InstrumentationRegistry.getInstrumentation().targetContext): ViewMatcher {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewMatcher(context)
                    }
                }
            }
            return INSTANCE!!
        }

        @JvmStatic
        fun get() = INSTANCE ?: throw IllegalStateException("Please call viewmatcher.initialize before using")
    }

}