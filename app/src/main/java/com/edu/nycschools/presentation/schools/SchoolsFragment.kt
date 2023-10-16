package com.edu.nycschools.presentation.schools

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.edu.nycschools.R
import com.edu.nycschools.data.dto.schools.Schools
import com.edu.nycschools.databinding.FragmentSchoolsBinding
import com.edu.nycschools.presentation.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Sealed class to handle state of the fragment.
 */
sealed class SchoolsFragmentState {
    data class Success(val schools: Schools) : SchoolsFragmentState()
    data object Error : SchoolsFragmentState()
    data object Idle : SchoolsFragmentState()
    data object HideSpinner : SchoolsFragmentState()
}


@AndroidEntryPoint
class SchoolsFragment : Fragment() {

    private lateinit var binding: FragmentSchoolsBinding

    private lateinit var schoolsAdapter: SchoolsAdapter

    private val schoolsViewModel by viewModels<SchoolsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schools, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        subscribe()
    }


    private fun initialize() {
        setUpToolBar()
        schoolsAdapter = SchoolsAdapter()
        binding.schoolsList.adapter = schoolsAdapter

        binding.swipeRefresh.setOnRefreshListener {
            lifecycleScope.launch {
                schoolsViewModel.fetchSchools()
            }
        }
    }


    private fun subscribe() {
        lifecycleScope.launch {
            schoolsViewModel.state.collect { state ->
                when (state) {
                    is SchoolsFragmentState.Success -> loadSchools(state.schools)
                    is SchoolsFragmentState.Idle -> showSpinner()
                    is SchoolsFragmentState.HideSpinner -> hideSpinner()
                    is SchoolsFragmentState.Error -> showError( binding.root)
                }
            }
        }
    }

    private fun showSpinner() {
        binding.swipeRefresh.isRefreshing = true
        binding.schoolsList.visibility = View.GONE
    }

    private fun hideSpinner() {
        binding.swipeRefresh.isRefreshing = false
    }


    private fun loadSchools(schools: Schools) {
        hideSpinner()
        binding.schoolsList.visibility = View.VISIBLE
        schoolsAdapter.submitList(schools)
    }

    private fun setUpToolBar() {
        with(binding.toolbar) {
            val view = (activity as AppCompatActivity)
            view.setSupportActionBar(this)
            view.title = getString(R.string.app_title)
        }
    }
}