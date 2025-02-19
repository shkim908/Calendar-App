package com.example.calendarapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.databinding.ItemListCalendarScheduleBinding
import com.example.calendarapp.ui.adapter.ScheduleAdapter.ScheduleViewHolder
import com.example.calendarapp.ui.widget.ScheduleList

class AddScheduleAdapter (private val scheduleList: List<ScheduleList>) : RecyclerView.Adapter<AddScheduleAdapter.AddScheduleViewHolder>() {
    class AddScheduleViewHolder(val binding: ItemListCalendarScheduleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddScheduleViewHolder {
        val binding = ItemListCalendarScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddScheduleViewHolder, position: Int) {
        val schedule = scheduleList[position]

        holder.binding.viewColor.setBackgroundColor(schedule.color)
        holder.binding.tvSchedule.text = schedule.title
        holder.binding.tvDatetime.text = schedule.time
    }

    override fun getItemCount(): Int = scheduleList.size

}