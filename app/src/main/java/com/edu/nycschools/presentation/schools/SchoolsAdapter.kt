package com.edu.nycschools.presentation.schools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.edu.nycschools.R
import com.edu.nycschools.data.dto.schools.School
import com.edu.nycschools.databinding.SchoolsResultsItemBinding

class SchoolsAdapter() : ListAdapter<School, SchoolViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<School>() {
            override fun areItemsTheSame(oldItem: School, newItem: School): Boolean =
                oldItem.dbn == newItem.dbn

            override fun areContentsTheSame(oldItem: School, newItem: School): Boolean =
                oldItem.dbn == newItem.dbn
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding: SchoolsResultsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.schools_results_item, parent, false
        )
        return SchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = getItem(position)
        if (school != null) {
            holder.bind(school)
        }
    }
}