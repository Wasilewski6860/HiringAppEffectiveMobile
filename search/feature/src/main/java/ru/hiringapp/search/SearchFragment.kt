package ru.hiringapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.search.data.SearchUiEvent
import ru.hiringapp.search.data.SearchUiState
import ru.hiringapp.search.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchUiState, SearchUiEvent>() {

    override val canPressBack: Boolean = false
    override val isRootFragment: Boolean = false

    lateinit var binding: FragmentSearchBinding
    override val viewModel: SearchViewModel by viewModels()

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

    override fun handleUiEvent(event: SearchUiEvent) {

    }

    override fun render(state: SearchUiState) {
//        bottomNavigationAdapter.items = state.tabs
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

}