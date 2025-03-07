package ru.hiringapp.main.feature

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.hiringapp.base.resources.ColorResources
import ru.hiringapp.base.resources.DrawableResource
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.base_feature.mvvm.State
import ru.hiringapp.base_feature.mvvm.UiEvent
import ru.hiringapp.base_feature.second_navigation.NavigationManager
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem
import ru.hiringapp.main.feature.data.MainUiEvent
import ru.hiringapp.main.feature.data.MainUiState
import ru.hiringapp.vacancy.api.usecase.ObserveFavouriteVacanciesCountUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val observeFavouriteVacanciesCountUseCase: ObserveFavouriteVacanciesCountUseCase,
    colorResources: ColorResources,
    drawableResource: DrawableResource,
    private val navigationManager: NavigationManager
) : BaseViewModel<MainUiState, MainUiEvent>() {

    override val _uiState = MutableStateFlow(MainUiState())
    override val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<MainUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()

    private val tabsController by lazy {
        TabsController(
            drawableResource = drawableResource,
            colorResources = colorResources,
            state = TabsControllerStateImpl(_uiState),
            navigationManager = navigationManager,
        )
    }

    init {
        observeFavouriteVacanciesCount()
    }

    private fun observeFavouriteVacanciesCount() {
        viewModelScope.launch {
            observeFavouriteVacanciesCountUseCase().collect { data ->
                tabsController.setFavouritesBadgeCount(data.takeIf { it != 0 })
            }
        }
    }
    fun onMainContainerLoaded(containerId: Int) {
        navigationManager.setNavigationContainer(containerId)
    }

    fun onBottomNavigationItemClicked(tabId: Int) {
        tabsController.manualSelectTab(tabId)
    }

    override fun onBackPressed() {
        navigationManager.navigateBack()
        tabsController.clearLast()
    }

    private class TabsControllerStateImpl(
        private val uiState: MutableStateFlow<MainUiState>,
    ) : TabsController.State {
        override var tabs: List<BottomNavigationItem>
            get() = uiState.value.tabs
            set(value) {
                uiState.update { it.copy(tabs = value) }
            }
    }
}