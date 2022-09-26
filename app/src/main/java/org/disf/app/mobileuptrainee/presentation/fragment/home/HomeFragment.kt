package org.disf.app.mobileuptrainee.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.disf.app.mobileuptrainee.databinding.FragmentHomeBinding
import org.disf.app.mobileuptrainee.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeState, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    private lateinit var cryptoAdapter: HomeAdapter

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    override fun prepareView() = with(binding) {
        cryptoAdapter = HomeAdapter(onItemClickedListener = viewModel::onMarketCoinClicked)
        cryptoListRv.adapter = cryptoAdapter
        moneyChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            when {
                checkedIds.contains(moneyUsdChip.id) -> viewModel.onUsdChipClicked()
                checkedIds.contains(moneyEurChip.id) -> viewModel.onEurChipClicked()
            }
        }
        zeroscreen.zeroscreenRetryButton.setOnClickListener {
            viewModel.onRetryQueryClicked()
        }
    }

    override fun collectState(state: HomeState) = with(binding) {
        zeroscreen.root.isVisible = state.isError && !state.isLoading
        cryptoListRv.isInvisible = state.isError
        loadingIndicator.isVisible = state.isLoading
        if (state.coinsMarket.isNotEmpty()) cryptoAdapter.setItems(state.coinsMarket)
    }
}