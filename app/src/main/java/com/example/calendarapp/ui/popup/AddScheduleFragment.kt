package com.example.calendarapp.ui.popup

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import com.example.calendarapp.databinding.FragmentAddScheduleBinding
import com.example.calendarapp.viewmodel.MainActivityViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.collections.hashMapOf as hashMapOf

class AddScheduleFragment : Fragment() {
    private lateinit var binding: FragmentAddScheduleBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    private var selectedHour = 9
    private var selectedMinute = 0

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
        initBtn()
    }

    private fun initView(){
        viewModel.selectedDate.observe(viewLifecycleOwner) { date ->
            binding.dateTv.text = date
        }
    }


    private fun initBtn(){

        binding.timeTv.setOnClickListener {
            val cal = Calendar.getInstance()
            val hour = if (selectedHour == -1) cal.get(Calendar.HOUR_OF_DAY) else selectedHour
            val minute = if (selectedMinute == -1) cal.get(Calendar.MINUTE) else selectedMinute
            val timeSetListener = OnTimeSetListener { view, hour, minute ->
                val amPm: String
                val hourFormatted: Int
                selectedHour = hour
                selectedMinute = minute
                if (hour >= 12) {
                    amPm = "PM"
                    hourFormatted = if (hour > 12) hour - 12 else hour
                } else {
                    amPm = "AM"
                    hourFormatted = if (hour == 0) 12 else hour
                }

                binding.hourMinTv.text = String.format("%d : %02d %s", hourFormatted, minute, amPm)
            }
            TimePickerDialog(requireContext(), timeSetListener,
                hour,
                minute,
                false).show()

        }

        binding.tvComplete.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val title = binding.titleEt.text.toString()
            val time = binding.hourMinTv.text.toString()
            val date = binding.dateTv.text.toString()
            val event = hashMapOf(
                "title" to title,
                "time" to time
            )

            db.collection("Schedule").document(date)
                .collection("events").add(event)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(requireContext(), "success!!!", Toast.LENGTH_SHORT).show()
                }
        }
    }

}