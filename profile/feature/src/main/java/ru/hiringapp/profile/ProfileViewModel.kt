package ru.hiringapp.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.hiringapp.base_feature.mvvm.BaseViewModel
import ru.hiringapp.profile.data.ProfileUiEvent
import ru.hiringapp.profile.data.ProfileUiState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : BaseViewModel<ProfileUiState, ProfileUiEvent>() {

    override val _uiState = MutableStateFlow(ProfileUiState())
    override val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    override val _uiEvent = MutableSharedFlow<ProfileUiEvent>()
    override val uiEvent = _uiEvent.asSharedFlow()


}
