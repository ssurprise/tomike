package com.skx.tomike.core

import android.app.Activity
import java.util.*

object AppActivityStackManager {

    private val mActivityStack = Stack<Activity>()

    fun put(activity: Activity) {
        mActivityStack.push(activity)
    }

    fun pop() {
        if (mActivityStack.isEmpty()) {
            return
        }
        mActivityStack.pop()
    }
}