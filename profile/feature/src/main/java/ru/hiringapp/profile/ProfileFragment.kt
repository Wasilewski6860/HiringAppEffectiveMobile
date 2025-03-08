package ru.hiringapp.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.hiringapp.base_feature.mvvm.BaseFragment
import ru.hiringapp.profile.data.ProfileUiEvent
import ru.hiringapp.profile.data.ProfileUiState
import ru.hiringapp.profile.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileUiState, ProfileUiEvent>() {

    lateinit var binding: FragmentProfileBinding
    override val viewModel: ProfileViewModel by viewModels()

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    override fun initViews() {

    }

    override fun handleUiEvent(event: ProfileUiEvent) {

    }

    override fun render(state: ProfileUiState) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

}