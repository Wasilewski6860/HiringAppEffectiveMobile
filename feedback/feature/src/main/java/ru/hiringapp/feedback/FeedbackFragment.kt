package ru.hiringapp.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.feedback.data.FeedbackUiEvent
import ru.hiringapp.feedback.data.FeedbackUiState
import ru.hiringapp.feedback.databinding.FragmentFeedbackBinding

@AndroidEntryPoint
class FeedbackFragment : BaseFragment<FeedbackUiState, FeedbackUiEvent>() {

    lateinit var binding: FragmentFeedbackBinding
    override val viewModel: FeedbackViewModel by viewModels()

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

    override fun handleUiEvent(event: FeedbackUiEvent) {

    }

    override fun render(state: FeedbackUiState) {
//        bottomNavigationAdapter.items = state.tabs
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        return binding.root
    }

}