package org.disf.app.mobileuptrainee.presentation.fragment.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket
import org.disf.app.mobileuptrainee.domain.model.VsCurrency
import org.disf.app.mobileuptrainee.domain.repository.CoinRepository
import org.disf.app.mobileuptrainee.presentation.base.BaseViewModel
import org.disf.app.mobileuptrainee.presentation.base.State
import org.disf.app.mobileuptrainee.presentation.ext.safeLaunch
import org.disf.app.mobileuptrainee.presentation.model.event.NavigationEvent
import org.disf.app.mobileuptrainee.presentation.model.event.ShowErrorSnackBar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
) : BaseViewModel<HomeState>() {

    override val initialState: HomeState
        get() = HomeState()

    init {
        loadMarketCoins()
    }

    fun onRetryQueryClicked() {
        loadMarketCoins()
    }

    fun onRefreshPulled() {
        loadMarketCoins()
    }

    fun onEurChipClicked() {
        updateState {
            copy(vsCurrency = VsCurrency.EUR)
        }
        loadMarketCoins()
    }

    fun onUsdChipClicked() {
        updateState {
            copy(vsCurrency = VsCurrency.USD)
        }
        loadMarketCoins()
    }

    fun onMarketCoinClicked(coinsMarket: CoinsMarket) {
        submitEvent(
            NavigationEvent(
                HomeFragmentDirections.actionHomeFragmentToDetailCryptoFragment(coinsMarket)
            )
        )
    }

    private fun loadMarketCoins() {
        updateState { copy(isLoading = true, isError = false) }
        viewModelScope.safeLaunch(
            block = {
                val coinsMarket = coinRepository.getMarketCoins(state.vsCurrency)
                updateState {
                    copy(
                        isLoading = false,
                        isError = false,
                        coinsMarket = coinsMarket
                    )
                }
            },
            onError = {
                if (state.coinsMarket.isNotEmpty()) {
                    updateState {
                        copy(isLoading = false)
                    }
                    submitEvent(ShowErrorSnackBar)
                } else {
                    updateState {
                        copy(isLoading = false, isError = true)
                    }
                }
            }
        )
    }

}

data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val coinsMarket: List<CoinsMarket> = listOf(),
    val vsCurrency: VsCurrency = VsCurrency.USD,
) : State