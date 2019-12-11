package com.cincinnatiai.grinchwalker.screenshot

import android.graphics.Bitmap
import androidx.annotation.VisibleForTesting
import androidx.test.runner.screenshot.ScreenCapture
import androidx.test.runner.screenshot.ScreenCaptureProcessor
import androidx.test.runner.screenshot.Screenshot
import java.io.IOException

interface GrinchCameraContract {
    fun takeScreenShot(
        testCaseName: String,
        directory: String
    )
}

class GrinchCamera : GrinchCameraContract {

    @VisibleForTesting
    val COMPRESS_FORMAT = Bitmap.CompressFormat.PNG

    @Volatile
    @VisibleForTesting
    var cachedDirectory: String = "screenshots/"
    @VisibleForTesting
    var screenShotProcessor: ScreenCaptureProcessor? = null

    override fun takeScreenShot(
        testName: String,
        directory: String
    ) {
        val shotProcessor = getScreenShotProcessor(directory)
        val capture = Screenshot.capture()
        capture.format = COMPRESS_FORMAT
        capture.name = testName
        processCapture(capture, shotProcessor)
    }

    @VisibleForTesting
    fun processCapture(
        capture: ScreenCapture,
        screenCaptureProcessor: ScreenCaptureProcessor
    ) {
        try {
            screenCaptureProcessor.process(capture)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @VisibleForTesting
    fun getScreenShotProcessor(directory: String): ScreenCaptureProcessor =
        if (cachedDirectory == directory && screenShotProcessor != null) {
            screenShotProcessor!!
        } else {
            cachedDirectory = directory
            GrinchScreenShotProcessor(directory).apply { screenShotProcessor = this }
        }
}