package org.disf.app.mobileuptrainee.presentation.fragment.detail

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import org.disf.app.mobileuptrainee.databinding.FragmentDetailCryptoBinding
import org.disf.app.mobileuptrainee.domain.model.CoinsMarket
import org.disf.app.mobileuptrainee.presentation.base.BaseFragment
import org.disf.app.mobileuptrainee.presentation.ext.load
import javax.inject.Inject

// TODO: Small image ?
@AndroidEntryPoint
class CoinDetailFragment :
    BaseFragment<FragmentDetailCryptoBinding, CoinDetailState, CoinDetailViewModel>() {

    @Inject
    internal lateinit var assistedFactory: CoinDetailViewModel.CoinDetailViewModelAssistedFactory

    private val coin: CoinsMarket by lazy {
        navArgs<CoinDetailFragmentArgs>().value.coin
    }

    override val viewModel: CoinDetailViewModel by viewModels {
        CoinDetailViewModel.providesFactory(
            assistedFactory,
            coin.id
        )
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentDetailCryptoBinding.inflate(inflater, container, false)
    }

    override fun prepareView() = with(binding) {
        toolbar.title = coin.name
        toolbar.setNavigationOnClickListener {
            viewModel.onNavigationIconClicked()
        }
    }

    override fun collectState(state: CoinDetailState): Unit = with(binding) {
        zeroscreen.root.isVisible = state.isError && !state.isLoading
        cryptoDetailContainer.isInvisible = state.isError || state.isLoading
        loadingIndicator.isVisible = state.isLoading
        state.coinDetail?.let { coinDescription ->
            cryptoDescriptionTv.text =
                HtmlCompat.fromHtml(coinDescription.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            cryptoDescriptionTv.movementMethod = LinkMovementMethod.getInstance()
            cryptoCategoryTv.text = coinDescription.categories.joinToString(separator = ", ")
            toolbar.title = coinDescription.name
            cryptoLogoIv.load(coinDescription.image)
        }
        zeroscreen.zeroscreenRetryButton.setOnClickListener {
            viewModel.onButtonRetryClicked()
        }
    }
}