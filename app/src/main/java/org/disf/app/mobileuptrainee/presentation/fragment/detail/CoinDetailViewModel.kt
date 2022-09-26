package org.disf.app.mobileuptrainee.presentation.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.disf.app.mobileuptrainee.domain.model.CoinDescription
import org.disf.app.mobileuptrainee.domain.repository.CoinRepository
import org.disf.app.mobileuptrainee.presentation.base.BaseViewModel
import org.disf.app.mobileuptrainee.presentation.base.State
import org.disf.app.mobileuptrainee.presentation.ext.safeLaunch
import org.disf.app.mobileuptrainee.presentation.model.event.PopNavigationEvent

class CoinDetailViewModel @AssistedInject constructor(
    private val coinRepository: CoinRepository,
    @Assisted private val coinId: String,
) : BaseViewModel<CoinDetailState>() {

    override val initialState: CoinDetailState
        get() = CoinDetailState(isLoading = true)

    init {
        loadCoinDescription()
    }

    fun onButtonRetryClicked() {
        loadCoinDescription()
    }

    fun onNavigationIconClicked() {
        submitEvent(PopNavigationEvent)
    }

    private fun loadCoinDescription() {
        updateState { copy(isLoading = true, isError = false) }
        viewModelScope.safeLaunch(
            block = {
                val coinDescription = coinRepository.getCoinDescription(coinId)
                updateState {
                    copy(
                        isLoading = false,
                        coinDetail = coinDescription
                    )
                }
            },
            onError = {
                updateState {
                    copy(
                        isError = true,
                        isLoading = false,
                    )
                }
            }
        )
    }

    @AssistedFactory
    interface CoinDetailViewModelAssistedFactory {
        fun create(coinId: String): CoinDetailViewModel
    }

    companion object {
        fun providesFactory(
            assistedFactory: CoinDetailViewModelAssistedFactory,
            coinId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(coinId) as T
            }
        }
    }
}

data class CoinDetailState(
    val coinDetail: CoinDescription? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
) : State