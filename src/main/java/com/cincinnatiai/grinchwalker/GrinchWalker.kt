package com.cincinnatiai.grinchwalker

import com.cincinnatiai.grinchwalker.model.GrinchConfig
import com.cincinnatiai.grinchwalker.screenshot.GrinchCamera
import com.cincinnatiai.grinchwalker.screenshot.GrinchCameraContract
import com.cincinnatiai.grinchwalker.utils.TestWaiter
import com.cincinnatiai.grinchwalker.utils.TestWaiterContract
import com.cincinnatiai.grinchwalker.view.ViewMatcher
import com.cincinnatiai.grinchwalker.view.ViewMatcherContract


class GrinchWalker private constructor(
    val grinchConfig: GrinchConfig
) {

    val camera: GrinchCameraContract by lazy { GrinchCamera() }
    val viewMatcher: ViewMatcherContract by lazy { ViewMatcher.initialize() }
    val testWaiter: TestWaiterContract by lazy { TestWaiter() }

    companion object {

        @Volatile
        private var INSTANCE: GrinchWalker = GrinchWalker(GrinchConfig())

        @JvmStatic
        fun get() = INSTANCE
    }
}