package ru.hiringapp.base_feature.mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<
        UiState : State,
        Event : UiEvent,
        ViewModel : BaseViewModel<UiState, Event>,
        ViewBinding : androidx.viewbinding.ViewBinding,
        >
    : AppCompatActivity(), NonInjectBaseView<UiState, Event, ViewModel> {

    protected abstract var binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setUiStateFlowCollect()
        setUiEventFlowCollect()
        setUiMessageEventFlowCollect()
    }

    override fun handleUiMessageEvent(messageEvent: Event) {
//        binding.root.onMessageEvent(messageEvent)
    }
}