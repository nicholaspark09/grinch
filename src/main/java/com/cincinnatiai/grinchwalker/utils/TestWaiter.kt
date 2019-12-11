package com.cincinnatiai.grinchwalker.utils

import android.app.Instrumentation
import androidx.annotation.LayoutRes
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until

private const val STATIC_WAIT = 600L
private const val DEFAULT_TIMEOUT = 900L

interface TestWaiterContract {

    fun waitUntilElementIsDisplayed(timeout: Long = DEFAULT_TIMEOUT, resourceId: String)

    fun staticWait(timeInMilliseconds: Long = STATIC_WAIT)

    fun waitUntilElementIsClickable(timeout: Long = DEFAULT_TIMEOUT, resourceId: String)

    fun waitUntilElementIsGone(timeout: Long = DEFAULT_TIMEOUT, resourceId: String)
}

class TestWaiter(
    val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation(),
    val device: UiDevice = UiDevice.getInstance(instrumentation)
) : TestWaiterContract {

    override fun waitUntilElementIsGone(timeout: Long, resourceId: String) {
        device.wait(Until.gone(By.res(resourceId)), timeout)
    }

    override fun waitUntilElementIsClickable(timeout: Long, resourceId: String) {
        device.wait(Until.findObject(By.clickable(true).res(resourceId)), timeout)
    }

    override fun waitUntilElementIsDisplayed(timeout: Long, resourceId: String) {
        device.findObject(UiSelector().resourceId(resourceId)).waitForExists(timeout)
    }

    override fun staticWait(timeInMilliseconds: Long) {
        try {
            Thread.sleep(timeInMilliseconds)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}