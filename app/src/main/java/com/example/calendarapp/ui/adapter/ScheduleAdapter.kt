package com.example.calendarapp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.R
import com.example.calendarapp.databinding.ItemListCalendarScheduleBinding
import com.example.calendarapp.ui.widget.ScheduleList
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.coroutineContext

class ScheduleAdapter(private val scheduleList: List<ScheduleList>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

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

        if(holder.binding.tvSchedule.text == "nothing"){
            holder.binding.tvDatetime.visibility = View.GONE
            holder.binding.recyclerV.alpha = 0.5f
        }

    }

    override fun getItemCount(): Int = scheduleList.size
}



