package com.example.parkingsystem.mvp.view.base

import android.content.Context
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

open class DialogFragmentView(fragment: Fragment) {
    private val fragmentRef: WeakReference<Fragment> = WeakReference(fragment)

    val fragment: Fragment?
        get() = fragmentRef.get()

    val context: Context?
        get() = fragment?.context
}
