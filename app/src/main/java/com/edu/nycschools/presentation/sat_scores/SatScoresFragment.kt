package com.edu.nycschools.presentation.sat_scores

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.edu.nycschools.R
import com.edu.nycschools.data.dto.sat.SatScoresForSchool
import com.edu.nycschools.databinding.FragmentSatScoresBinding
import com.edu.nycschools.presentation.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Sealed class to track fragment state.
 */
sealed class SatScoresFragmentState {
    data class Success(val scores: SatScoresForSchool) : SatScoresFragmentState()
    data object Error : SatScoresFragmentState()
    data object Idle : SatScoresFragmentState()
}

@AndroidEntryPoint
class SatScoresFragment : Fragment() {
    private lateinit var binding: FragmentSatScoresBinding

    private val schoolsViewModel by viewModels<SatScoresViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sat_scores, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }


    private fun initialize() {
        setUpToolBar()
        val dbn = arguments?.getString("dbn")
        if (dbn != null) {
            schoolsViewModel.getSatScores(dbn)
        }
    }

    private fun subscribe() {
        lifecycleScope.launch {
            schoolsViewModel.state.collect { state ->
                when (state) {
                    is SatScoresFragmentState.Success -> displayScores(state.scores)
                    is SatScoresFragmentState.Idle -> showSpinner()
                    is SatScoresFragmentState.Error -> displayError()
                }
            }
        }
    }

    private fun showSpinner() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideSpinner() {
        binding.progressCircular.visibility = View.GONE
    }

    private fun displayScores(scores: SatScoresForSchool) {
        hideSpinner()
        if (scores.isEmpty()) {
            showError(binding.root)
        } else {
            binding.scoresLayout.visibility = View.VISIBLE
            binding.scores = scores[0]
        }
    }


    /**
     * Display error
     */
    private fun displayError() {
        hideSpinner()
        showError(binding.root)
    }

    /**
     * Set up the toolbar
     */
    private fun setUpToolBar() {
        with(binding.toolbar) {
            val view = (activity as AppCompatActivity)
            view.setSupportActionBar(this)
            view.title = getString(R.string.sat_scores_title)
            setNavigationIcon(R.drawable.baseline_arrow_back_24)
            setNavigationOnClickListener {
                Navigation.findNavController(binding.root).popBackStack()
            }
        }
    }
}