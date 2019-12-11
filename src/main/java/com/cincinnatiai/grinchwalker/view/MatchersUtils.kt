package com.cincinnatiai.grinchwalker.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun getTextFromRecyclerViewItem(recyclerViewId: Int, adapterPosition: Int, targetViewId: Int): String {
    val textHolder = mutableListOf<String>()
    val recyclerTextMatcher = object: TypeSafeMatcher<View>() {
        override fun matchesSafely(item: View): Boolean {
            val viewHolder: RecyclerView.ViewHolder? = (item as RecyclerView).findViewHolderForAdapterPosition(adapterPosition)
            if (viewHolder != null) {
                val textView = viewHolder.itemView.findViewById<TextView>(targetViewId)
                textHolder.add(textView.text.toString())
            }
            return true
        }

        override fun describeTo(description: Description?) { } // NOOP
    }
    onView(withId(recyclerViewId)).check(matches(recyclerTextMatcher))
    return textHolder.firstOrNull() ?: ""
}