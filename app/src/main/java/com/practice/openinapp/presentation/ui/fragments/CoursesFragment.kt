package com.practice.openinapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.openinapp.R
import com.practice.openinapp.databinding.FragmentCoursesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesFragment : Fragment(R.layout.fragment_courses) {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}