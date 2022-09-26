package org.disf.app.mobileuptrainee.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.disf.app.mobileuptrainee.R
import org.disf.app.mobileuptrainee.presentation.model.event.NavigationEvent
import org.disf.app.mobileuptrainee.presentation.model.event.PopNavigationEvent
import org.disf.app.mobileuptrainee.presentation.model.event.ShowErrorSnackBar

/**
 * Абстрактный класс фрагмента
 * @param B - Вьюбиндинг View
 * @param S - Класс стейта View
 * @param V - Класс вьюмодели фрагмента
 */
abstract class BaseFragment<B : ViewBinding, S : State, V : BaseViewModel<S>> : Fragment() {

    abstract val viewModel: V

    protected var _binding: B? = null
    protected val binding: B
        get() = _binding!!

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?)
    abstract fun collectState(state: S)
    abstract fun prepareView()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect(this@BaseFragment::collectState)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collect(this@BaseFragment::collectEvent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun collectEvent(event: Event) {
        when (event) {
            is ShowErrorSnackBar -> {
                Snackbar.make(
                    binding.root,
                    getString(R.string.error_happens),
                    Snackbar.LENGTH_SHORT
                )
                    .setBackgroundTint(requireContext().getColor(R.color.error))
                    .show()
            }
            is NavigationEvent -> {
                findNavController().navigate(event.direction)
            }
            is PopNavigationEvent -> {
                findNavController().popBackStack()
            }
        }
    }

}