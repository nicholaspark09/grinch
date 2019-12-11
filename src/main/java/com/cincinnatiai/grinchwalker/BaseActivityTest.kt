package com.cincinnatiai.grinchwalker

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import com.cincinnatiai.grinchwalker.utils.ActivityCloser
import com.cincinnatiai.grinchwalker.view.ViewMatcherContract
import com.cincinnatiai.grinchwalker.watcher.GrinchTestWatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestWatcher
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class BaseActivityTest<T>(
    target: Class<T>,
    isScreenshotsEnabled: Boolean = false
) {

    @get:Rule
    var activityRule: ActivityTestRule<Activity> =
        ActivityTestRule<Activity>(target as Class<Activity>)

    @get:Rule
    var testWatcher: TestWatcher = GrinchTestWatcher(isScreenshotsEnabled = isScreenshotsEnabled)

    @get:Rule
    var permissionRule = GrantPermissionRule.grant(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)

    @get:Rule
    var ruleChain: RuleChain = RuleChain.emptyRuleChain()
        .around(testWatcher)
        .around(permissionRule)

    private lateinit var uiDevice: UiDevice

    val viewMatcher: ViewMatcherContract by lazy { GrinchWalker.get().viewMatcher }

    @Before
    open fun setup() {
        activityRule.activity
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @After
    fun tearDown() {
        ActivityCloser.finishAllActivities()
    }
}