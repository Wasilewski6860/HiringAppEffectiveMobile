package ru.hiringapp.base_feature.activity_provider

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.internal.GeneratedComponentManagerHolder
import javax.inject.Inject

@EntryPoint
@InstallIn(FragmentComponent::class)
interface FragmentProviderEntryPoint {
    val fragmentProvider: CurrentFragmentProvider
}

@ActivityRetainedScoped
class CurrentFragmentProvider @Inject constructor() {

    private var currentFragment: Fragment? = null

    fun <T> withFragment(block: Fragment.() -> T): T {
        checkMainThread()

        val fragment = currentFragment
        check(fragment != null) {
            "Don't call this after the fragment is finished!"
        }
        return fragment.block()
    }

    fun get(): Fragment? = currentFragment

    companion object {

        private fun Fragment.withProvider(
            block: CurrentFragmentProvider.() -> Unit
        ) {
            if (this is GeneratedComponentManagerHolder) {
                val entryPoint: FragmentProviderEntryPoint =
                    EntryPointAccessors.fromFragment(this)
                val provider = entryPoint.fragmentProvider
                provider.block()
            }
        }

        fun onFragmentCreated(fragment: Fragment) {
            fragment.withProvider {
                if (fragment !is NotScreen) {
                    currentFragment = fragment
                }
            }
        }

        fun onFragmentDestroyed(fragment: Fragment) {
            fragment.withProvider {
                if (currentFragment === fragment) {
                    currentFragment = null
                }
            }
        }
    }
}

class FragmentProviderByActivityProviderCallbacks: Application.ActivityLifecycleCallbacks {

    private lateinit var fragmentProviderCallbacks: FragmentProviderCallbacks

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        fragmentProviderCallbacks = FragmentProviderCallbacks(FragmentStateErrorTrackerImpl())
        (activity as? AppCompatActivity)
            ?.supportFragmentManager
            ?.registerFragmentLifecycleCallbacks(fragmentProviderCallbacks, true)
    }

    override fun onActivityDestroyed(activity: Activity) {
        (activity as? AppCompatActivity)
            ?.supportFragmentManager
            ?.unregisterFragmentLifecycleCallbacks(fragmentProviderCallbacks)
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
}

internal class FragmentProviderCallbacks(
    private val fragmentStateErrorTracker: FragmentStateErrorTracker
) : FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        CurrentFragmentProvider.onFragmentCreated(f)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        CurrentFragmentProvider.onFragmentDestroyed(f)
        super.onFragmentDestroyed(fm, f)
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        fragmentStateErrorTracker.trackResume(f)
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        fragmentStateErrorTracker.trackPause(f)
    }
}
