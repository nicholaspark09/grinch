package com.cincinnatiai.grinchwalker.screenshot

import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import java.io.File

class GrinchScreenShotProcessor private constructor(file: File) : BasicScreenCaptureProcessor() {

    constructor(directory: String) : this(
        File(
            File(getExternalStoragePublicDirectory(DIRECTORY_PICTURES), "Grinch").getAbsolutePath(),
            "screenshots/$directory"
        )
    )

    init {
        mTag = "GrinchScreenCaptureProcessor"
        mFileNameDelimiter = "-"
        mDefaultFilenamePrefix = "screenshot"
        mDefaultScreenshotPath = file
    }
}
