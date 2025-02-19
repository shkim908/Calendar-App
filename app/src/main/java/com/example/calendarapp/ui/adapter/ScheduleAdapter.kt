package com.example.calendarapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.databinding.ItemListCalendarScheduleBinding
import com.example.calendarapp.ui.widget.ScheduleList

class ScheduleAdapter(private val scheduleList: List<ScheduleList>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    // ViewHolder 클래스
    class ScheduleViewHolder(val binding: ItemListCalendarScheduleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemListCalendarScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = scheduleList[position]

        holder.binding.viewColor.setBackgroundColor(schedule.color)
        holder.binding.tvSchedule.text = schedule.title
        holder.binding.tvDatetime.text = schedule.time
    }

    override fun getItemCount(): Int = scheduleList.size
}



