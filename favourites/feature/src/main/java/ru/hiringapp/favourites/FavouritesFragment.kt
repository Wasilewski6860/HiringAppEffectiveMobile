package ru.hiringapp.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.favourites.data.FavouritesUiEvent
import ru.hiringapp.favourites.data.FavouritesUiState
import ru.hiringapp.favourites.databinding.FragmentFavouritesBinding

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FavouritesUiState, FavouritesUiEvent>() {

    override val canPressBack: Boolean = false
    override val isRootFragment: Boolean = false

    lateinit var binding: FragmentFavouritesBinding
    override val viewModel: FavouritesViewModel by viewModels()

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

    override fun handleUiEvent(event: FavouritesUiEvent) {

    }

    override fun render(state: FavouritesUiState) {
//        bottomNavigationAdapter.items = state.tabs
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

}