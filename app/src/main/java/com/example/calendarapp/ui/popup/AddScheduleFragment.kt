package com.example.calendarapp.ui.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.calendarapp.R

import com.example.calendarapp.databinding.FragmentAddScheduleBinding
import com.example.calendarapp.ui.adapter.AddScheduleAdapter
import com.example.calendarapp.ui.adapter.ScheduleAdapter
import com.example.calendarapp.ui.widget.ScheduleList
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AddScheduleFragment : Fragment() {
    private lateinit var binding: FragmentAddScheduleBinding
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initList()
    }

    private fun initView(){

    }

    private fun initList(){
        val scheduleList = ArrayList<ScheduleList>().apply {
            add(ScheduleList(ContextCompat.getColor(requireContext(), R.color.calender_color_red), "Meeting", "10:00 AM"))
            add(ScheduleList(ContextCompat.getColor(requireContext(), R.color.calender_color_orange), "Lunch", "12:00 PM"))
            add(ScheduleList(ContextCompat.getColor(requireContext(), R.color.calender_color_blue), "Conference", "2:00 PM"))
        }

//        binding.recyclerViewSchedule.adapter = AddScheduleAdapter(scheduleList)
    }
}