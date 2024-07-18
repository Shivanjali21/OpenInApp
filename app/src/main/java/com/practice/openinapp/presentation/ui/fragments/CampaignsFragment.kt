package com.practice.openinapp.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.openinapp.R
import com.practice.openinapp.databinding.FragmentCampaignsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampaignsFragment : Fragment(R.layout.fragment_campaigns) {

    private var _binding: FragmentCampaignsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCampaignsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}