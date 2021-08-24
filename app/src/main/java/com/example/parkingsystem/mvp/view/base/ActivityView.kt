package com.example.parkingsystem.mvp.view.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {
    private val activityRef: WeakReference<Activity> = WeakReference(activity)

    val activity: Activity?
        get() = activityRef.get()

    val fragmentManager: FragmentManager
        get() = (activity as AppCompatActivity).supportFragmentManager

    val context: Context?
        get() = activity

}
