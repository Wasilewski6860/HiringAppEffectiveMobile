package ru.hiringapp.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.extensions.dp
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.base_feature.view.decoration.SpacingItemDecorationBuilder
import ru.hiringapp.favourites.data.FavouritesUiEvent
import ru.hiringapp.favourites.data.FavouritesUiState
import ru.hiringapp.favourites.databinding.FragmentFavouritesBinding
import ru.hiringapp.uikit.R
import ru.hiringapp.vacancy.createVacanciesAdapter

@AndroidEntryPoint
internal class FavouritesFragment : BaseFragment<FavouritesUiState, FavouritesUiEvent>() {

    override val canPressBack: Boolean = false
    override val isRootFragment: Boolean = false

    lateinit var binding: FragmentFavouritesBinding
    override val viewModel: FavouritesViewModel by viewModels()

    private val vacanciesAdapter by lazy {
        createVacanciesAdapter(
            onVacancyApplyButtonItemClick = viewModel::onVacancyApplyBtnClick,
            onVacancyFavouriteButtonItemClick = viewModel::onVacancyFavouriteBtnClick,
        )
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {
        setupRvVacancies()
    }

    override fun handleUiEvent(event: FavouritesUiEvent) {

    }

    override fun render(state: FavouritesUiState) {
        binding.tvVacanciesCount.text = state.vacanciesCountText
        vacanciesAdapter.items = state.vacancies
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRvVacancies() {
        with(binding.rvVacancies) {
            adapter = vacanciesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                SpacingItemDecorationBuilder()
                    .setOrientation(LinearLayoutManager.VERTICAL)
                    .setSpacing(bottom = 8.dp)
                    .build()
            )
            itemAnimator = null
        }
    }
}