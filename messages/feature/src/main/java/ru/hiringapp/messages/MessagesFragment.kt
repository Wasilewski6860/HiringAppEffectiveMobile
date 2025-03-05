package ru.hiringapp.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.messages.data.MessagesUiEvent
import ru.hiringapp.messages.data.MessagesUiState
import ru.hiringapp.messages.databinding.FragmentMessagesBinding

@AndroidEntryPoint
class MessagesFragment : BaseFragment<MessagesUiState, MessagesUiEvent>() {

    override val canPressBack: Boolean = false
    override val isRootFragment: Boolean = false

    lateinit var binding: FragmentMessagesBinding
    override val viewModel: MessagesViewModel by viewModels()

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {
//        with(binding) {
//            rvTabs.apply {
//                adapter = bottomNavigationAdapter
//                layoutManager = FlexboxLayoutManager(context).apply {
//                    flexWrap = FlexWrap.NOWRAP
//                }
//                itemAnimator = null
//            }
//        }
//        applySystemBottomInsets()
    }

    override fun handleUiEvent(event: MessagesUiEvent) {

    }

    override fun render(state: MessagesUiState) {
//        bottomNavigationAdapter.items = state.tabs
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

}