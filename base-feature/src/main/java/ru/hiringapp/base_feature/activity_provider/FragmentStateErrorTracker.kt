package ru.hiringapp.base_feature.activity_provider

import androidx.fragment.app.Fragment

/**
 * Логирует состояние экранов приложения для отчетов ошибок в крашлитике
 */
internal interface FragmentStateErrorTracker {
    /**
     * Логирование состояния resume для экрана, представленного [fragment]
     */
    fun trackResume(fragment: Fragment)

    /**
     * Логирование состояния pause для экрана, представленного [fragment]
     */
    fun trackPause(fragment: Fragment)
}

internal class FragmentStateErrorTrackerImpl : FragmentStateErrorTracker {
    override fun trackResume(fragment: Fragment) {

    }

    override fun trackPause(fragment: Fragment){

    }
}