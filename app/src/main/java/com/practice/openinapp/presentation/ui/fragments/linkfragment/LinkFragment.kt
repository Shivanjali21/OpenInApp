package com.practice.openinapp.presentation.ui.fragments.linkfragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.practice.openinapp.R
import com.practice.openinapp.databinding.FragmentLinkBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.practice.openinapp.utils.connectivity.NetworkManager
import com.practice.openinapp.data.OverallUrlChart
import com.practice.openinapp.data.TopSectionData
import com.practice.openinapp.domain.sharedpref.SharedPref
import com.practice.openinapp.utils.Constant
import com.practice.openinapp.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.json.JSONObject

@AndroidEntryPoint
class LinkFragment : Fragment(R.layout.fragment_link) {

    private var _binding: FragmentLinkBinding? = null
    private val binding get() = _binding!!
    private val mainViewModels: MainViewModel by viewModels()
    private var buttonTopClick: Boolean = true
    private lateinit var topSectionAdapter: TopSectionAdapter
    private lateinit var topClickAdapter: TopLinksAdapter
    private lateinit var recentClickAdapter: RecentLinkAdapter
    private val token = Constant.TOKEN
    private var items = listOf<TopSectionData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLinkBinding.inflate(inflater, container, false)

        val sharedPreferencesHelper = SharedPref(requireContext())
        sharedPreferencesHelper.saveToken(token)

        btnColorChange()
        checkInternet()

        binding.mcvTopLink.setOnClickListener {
            buttonTopClick = true
            btnColorChange()
            binding.rvTopRecentLink.adapter = topClickAdapter
        }

        binding.mcvRecentLink.setOnClickListener {
            buttonTopClick = false
            btnColorChange()
            binding.rvTopRecentLink.adapter = recentClickAdapter
        }

        lineChartShow()

        return binding.root
    }

    private fun checkInternet() {
        val networkManager = NetworkManager(requireContext())
        networkManager.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.apply {
                    progressBar.visibility = View.VISIBLE
                    contentView.visibility = View.GONE
                    ivNoInternet.visibility = View.GONE
                    mtvNoInternet.visibility = View.GONE
                }
                topSection()
            } else {
                binding.apply {
                    progressBar.visibility = View.GONE
                    contentView.visibility = View.GONE
                    ivNoInternet.visibility = View.VISIBLE
                    mtvNoInternet.visibility = View.VISIBLE
                    mtvNoInternet.text = resources.getString(R.string.no_internet)
                }
            }
        }
    }

    private fun btnColorChange() {
        if (buttonTopClick) {
            binding.mcvTopLink.setCardBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.colorNavbar)
            )
            binding.mtvTopTag.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))

            binding.mcvRecentLink.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_grey
                )
            )
            binding.mtvRecentTag.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBtmInActive))

        } else {
            binding.mcvTopLink.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_grey
                )
            )
            binding.mtvTopTag.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorBtmInActive))

            binding.mcvRecentLink.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorNavbar
                )
            )
            binding.mtvRecentTag.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        }
    }

    private fun topSection() {
        lifecycleScope.launch {
            mainViewModels.getDashboardData(token = token).observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE

                if (it == null) {
                    binding.contentView.visibility = View.GONE
                    binding.ivNoInternet.visibility = View.GONE
                    binding.mtvNoInternet.visibility = View.VISIBLE
                    binding.mtvNoInternet.text = resources.getString(R.string.no_data)
                } else {
                    binding.contentView.visibility = View.VISIBLE
                    binding.mtvNoInternet.visibility = View.GONE
                    binding.ivNoInternet.visibility = View.GONE

                    binding.mtvDate.text = it.startTime
                    items = listOf(
                        TopSectionData(
                            R.drawable.ic_todayclick,
                            it.today_clicks.toString(),
                            "Today's Click"
                        ),
                        TopSectionData(R.drawable.ic_location, it.top_location, "Top Location"),
                        TopSectionData(R.drawable.ic_insta, it.top_source, "Top Source"),
                    )
                    topSectionAdapter = TopSectionAdapter(requireContext())
                    binding.rvTopSection.adapter = topSectionAdapter
                    topSectionAdapter.asyncDiffUtil.submitList(items)

                    topClickAdapter = TopLinksAdapter()
                    binding.rvTopRecentLink.adapter = topClickAdapter
                    topClickAdapter.asyncDiffUtil.submitList(it.data.top_links)

                    recentClickAdapter = RecentLinkAdapter()
                    recentClickAdapter.asyncDiffUtil.submitList(it.data.recent_links)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun lineChartShow() {
        val jsonResponse = """{
            "status": true,
            "statusCode": 200,
            "message": "success",
            "data": {
                "overall_url_chart": {
                    "00:00": 0,
                    "01:00": 0,
                    "02:00": 1,
                    "03:00": 0,
                    "04:00": 0,
                    "05:00": 0,
                    "06:00": 0,
                    "07:00": 0,
                    "08:00": 0,
                    "09:00": 0,
                    "10:00": 3,
                    "11:00": 1,
                    "12:00": 1,
                    "13:00": 0,
                    "14:00": 0,
                    "15:00": 0,
                    "16:00": 0,
                    "17:00": 0,
                    "18:00": 0,
                    "19:00": 0,
                    "20:00": 0,
                    "21:00": 0,
                    "22:00": 0,
                    "23:00": 0
                }
            }
        }"""

        val gson = Gson()
        val jsonObject = JSONObject(jsonResponse)
        val dataObject = jsonObject.getJSONObject("data").toString()
        val overallUrlChart = gson.fromJson(dataObject, OverallUrlChart::class.java)

        val entries = convertToEntries(overallUrlChart)
        setupLineChart(entries)

    }

    private fun convertToEntries(chart: OverallUrlChart): List<Entry> {
        val times = chart.chart.keys.toList().sorted()
        return times.mapIndexed { index, key ->
            Entry(index.toFloat(), chart.chart[key]?.toFloat() ?: 0f)
        }
    }

    @SuppressLint("NewApi")
    private fun setupLineChart(entries: List<Entry>) {
        val dataSet = LineDataSet(entries, "Overall URL Chart")
        dataSet.color = resources.getColor(R.color.colorNavbar, null)
        dataSet.valueTextColor = resources.getColor(R.color.colorRed, null)

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        val xAxis = binding.lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.valueFormatter = IndexAxisValueFormatter((0..23).map { String.format("%02d:00", it) })

        binding.lineChart.invalidate()

    }
}