package com.cincinnatiai.grinchwalker.utils

import android.os.Handler
import android.os.Looper
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import java.util.*

class ActivityCloser : Runnable {

    private val monitor: ActivityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance()

    override fun run() {
        EnumSet.range(Stage.CREATED, Stage.STOPPED).forEach { stage ->
            stopActivitiesInStage(stage)
        }
    }

    private fun stopActivitiesInStage(stage: Stage) {
        monitor.getActivitiesInStage(stage).forEach { activity ->
            if (activity.isFinishing.not()) {
                activity.finish()
            }
        }
    }

    companion object {

        fun finishAllActivities() {
            Handler(Looper.getMainLooper()).post(ActivityCloser())
        }
    }
}