package com.edu.nycschools.presentation.schools

import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.edu.nycschools.R
import com.edu.nycschools.data.dto.schools.School
import com.edu.nycschools.databinding.SchoolsResultsItemBinding

class SchoolViewHolder(private val binding : SchoolsResultsItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(schoolItem : School){
        with(binding){
            school = schoolItem
            val bundle = bundleOf("dbn" to schoolItem.dbn)
            schoolLayout.setOnClickListener {
                it.findNavController().navigate(R.id.satScoresFragment, bundle)
            }
        }
    }
}