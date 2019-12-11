package com.cincinnatiai.grinchwalker.watcher

import androidx.annotation.VisibleForTesting
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.cincinnatiai.grinchwalker.GrinchWalker
import com.cincinnatiai.grinchwalker.annotation.GrinchTestCase
import com.cincinnatiai.grinchwalker.screenshot.GrinchCameraContract
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class GrinchTestWatcher(
    @VisibleForTesting val isScreenshotsEnabled: Boolean = GrinchWalker.get().grinchConfig.takeScreenShots,
    @VisibleForTesting val grinchCamera: GrinchCameraContract = GrinchWalker.get().camera
) : TestWatcher() {

    override fun failed(e: Throwable?, description: Description) {
        val grinchTestCase = description.getAnnotation(GrinchTestCase::class.java)
        if (isScreenShotRouteActive(grinchTestCase)) {
            takeScreenShot(grinchTestCase.testCaseName, description)
        }
    }

    @VisibleForTesting
    fun isScreenShotRouteActive(annotation: GrinchTestCase?) =
        isScreenshotsEnabled && annotation != null

    @VisibleForTesting
    @Synchronized
    fun takeScreenShot(testName: String, description: Description) {
        runOnUiThread {
            grinchCamera.takeScreenShot(testName, "testFailures/${description.className}")
        }
    }
}