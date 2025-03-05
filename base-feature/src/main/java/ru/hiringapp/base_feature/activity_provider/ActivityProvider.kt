package ru.hiringapp.base_feature.activity_provider

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.os.Looper
import androidx.annotation.MainThread
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.internal.GeneratedComponentManagerHolder
import javax.inject.Inject

val isMainThread: Boolean get() = Looper.getMainLooper().thread === Thread.currentThread()

fun checkMainThread() {
    check(isMainThread) {
        "Should be called from the main thread, not ${Thread.currentThread()}"
    }
}

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ActivityProviderEntryPoint {
    val activityProvider: CurrentActivityProvider
}

@ActivityRetainedScoped
class CurrentActivityProvider @Inject constructor() {

    private var currentActivity: Activity? = null

    /**
     * Получить конекст текущей активити
     */
    fun getContext(): Context? = currentActivity

    @MainThread
    fun <T> withActivity(block: Activity.() -> T): T {
        checkMainThread()
        val activity = currentActivity
        check(activity != null) {
            "Don't call this after the activity is finished!"
        }
        return activity.block()
    }

    companion object {
        private fun Activity.withProvider(
            block: CurrentActivityProvider.() -> Unit
        ) {
            if (this is GeneratedComponentManagerHolder) {
                val entryPoint: ActivityProviderEntryPoint =
                    EntryPointAccessors.fromActivity(this)
                val provider = entryPoint.activityProvider
                provider.block()
            }
        }

        fun onActivityCreated(activity: Activity) {
            activity.withProvider {
                currentActivity = activity
            }
        }

        fun onActivityDestroyed(activity: Activity) {
            activity.withProvider {
                if (currentActivity === activity) {
                    currentActivity = null
                }
            }
        }
    }
}

class ActivityProviderCallbacks : ActivityLifecycleCallbacks {

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        CurrentActivityProvider.onActivityCreated(activity)
    }

    override fun onActivityDestroyed(activity: Activity) {
        CurrentActivityProvider.onActivityDestroyed(activity)
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
}