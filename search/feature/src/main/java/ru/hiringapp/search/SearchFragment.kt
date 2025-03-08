package ru.hiringapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.extensions.dp
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.base_feature.view.decoration.EqualHeightItemDecoration
import ru.hiringapp.base_feature.view.decoration.SpacingItemDecorationBuilder
import ru.hiringapp.offers.createOffersAdapter
import ru.hiringapp.search.data.SearchUiEvent
import ru.hiringapp.search.data.SearchUiState
import ru.hiringapp.search.databinding.FragmentSearchBinding
import ru.hiringapp.uikit.R
import ru.hiringapp.vacancy.createVacanciesAdapter

@AndroidEntryPoint
internal class SearchFragment : BaseFragment<SearchUiState, SearchUiEvent>() {

    lateinit var binding: FragmentSearchBinding
    override val viewModel: SearchViewModel by viewModels()

    private val offersAdapter by lazy {
        createOffersAdapter(
            onOfferItemClick = viewModel::onOfferClick,
        )
    }

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
        setupRvOffers()
        setupRvVacancies()
        initListeners()
    }

    override fun handleUiEvent(event: SearchUiEvent) {

    }

    override fun render(state: SearchUiState) {
        with(binding) {
            when (state.isExpanded) {
                true -> {
                    rvOffers.isVisible = false
                    tvVacanciesForYou.isVisible = false
                    vacanciesInfoContainer.isVisible = true
                    tvVacanciesCount.text = state.allVacanciesText
                    btnLoadMore.isVisible = false
                    searchBar.setHintText(R.string.searchAppBar_hint_expanded)
                    searchBar.setAction(R.drawable.ic_action_back, viewModel::onActionBackClick)
                }

                false -> {
                    rvOffers.isVisible = true
                    tvVacanciesForYou.isVisible = true
                    vacanciesInfoContainer.isVisible = false
                    btnLoadMore.isVisible = true
                    btnLoadMore.text = state.additionalVacanciesText
                    searchBar.setHintText(R.string.searchAppBar_hint_standart)
                    searchBar.setAction(R.drawable.ic_search_not_selected, {})
                }
            }
        }
        offersAdapter.items = state.offers
        vacanciesAdapter.items = state.vacancies
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initListeners() {
        with(binding) {
            btnLoadMore.setOnClickListener {
                viewModel.onLoadMoreBtnClick()
            }
        }
    }

    private fun setupRvOffers() {
        with(binding.rvOffers) {
            adapter = offersAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(
                SpacingItemDecorationBuilder()
                    .setOrientation(LinearLayoutManager.HORIZONTAL)
                    .setEdges(top = 16.dp, right = 16.dp, bottom = 0, left = 16.dp)
                    .setSpacing(right = 8.dp)
                    .build()
            )
            addItemDecoration(EqualHeightItemDecoration())
            itemAnimator = null
        }
    }

    private fun setupRvVacancies() {
        with(binding.rvVacancies) {
            adapter = vacanciesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                SpacingItemDecorationBuilder()
                    .setOrientation(LinearLayoutManager.VERTICAL)
                    .setEdges(top = 16.dp, right = 16.dp, bottom = 0, left = 16.dp)
                    .setSpacing(bottom = 8.dp)
                    .build()
            )
            itemAnimator = null
        }
    }

}