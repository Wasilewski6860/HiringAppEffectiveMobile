package ru.hiringapp.main.feature

import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.hiringapp.base_feature.extensions.afterMeasured
import ru.hiringapp.base_feature.second_navigation.NavigationManager
import ru.hiringapp.base_feature.utils.setPaddingToInset
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItem
import ru.hiringapp.main.feature.bottom_navigation.BottomNavigationItemsClickListenerImpl
import ru.hiringapp.main.feature.data.MainUiState
import ru.hiringapp.main.feature.databinding.ActivityMainBinding
import ru.hiringapp.main.feature.list.BottomNavigationAdapter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    val viewModel by viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding

    private val backPressedCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.onBackPressed()
            }
        }
    }

    private val bottomNavigationItemsClickListener by lazy {
        BottomNavigationItemsClickListenerImpl { bottomNavigationItem: BottomNavigationItem ->
            viewModel.onBottomNavigationItemClicked(bottomNavigationItem.id)
        }
    }


    private val bottomNavigationAdapter by lazy {
        BottomNavigationAdapter(bottomNavigationItemsClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationManager.setFragmentManager(supportFragmentManager)
        viewModel.onMainContainerLoaded(binding.globalFragmentContainerView.id)
        onBackPressedDispatcher.addCallback(this, backPressedCallback)

        initViews()
        setUiStateFlowCollect()
    }

    override fun onDestroy() {
        binding.rvTabs.adapter = null
        super.onDestroy()
    }

    fun initViews() {
        with(binding) {
            rvTabs.apply {
                adapter = bottomNavigationAdapter
                layoutManager = FlexboxLayoutManager(context).apply {
                    flexWrap = FlexWrap.NOWRAP
                }
                itemAnimator = null
            }
        }
        applySystemBottomInsets()
    }

    fun setUiStateFlowCollect() {
        lifecycleScope.launch {
            viewModel
                .uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    render(it)
                }
        }
    }

    fun render(state: MainUiState) {
        bottomNavigationAdapter.items = state.tabs
    }

    private fun applySystemBottomInsets() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            binding.globalFragmentContainerView setPaddingToInset statusBars()
            binding.rvTabs setPaddingToInset navigationBars()
//            binding.fragmentContainerView setPaddingToInset statusBars()
        }
    }
}
