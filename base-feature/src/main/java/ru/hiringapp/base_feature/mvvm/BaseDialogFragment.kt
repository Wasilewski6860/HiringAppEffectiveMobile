package ru.hiringapp.base_feature.mvvm

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseDialogFragment<ScreenState : State, ScreenEvent : UiEvent>(
    @LayoutRes layoutRes: Int
) : DialogFragment(layoutRes),
    BaseView<ScreenState, ScreenEvent> {

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setUiStateFlowCollect()
        setUiEventFlowCollect()
    }

    override fun setUiStateFlowCollect() {
        launch {
            setUiStateFlow()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::render)
        }
    }

    override fun setUiEventFlowCollect() {
        launch {
            setUiEventFlow()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect(::handleUiEvent)
        }
    }

    abstract fun handleUiEvent(event: ScreenEvent)
}
