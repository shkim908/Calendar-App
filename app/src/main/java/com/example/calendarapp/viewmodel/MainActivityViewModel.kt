package com.example.calendarapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivityViewModel : ViewModel() {
    private val mSelectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = mSelectedDate

    fun setSelectedDate(date: CalendarDay) {
        val calendar = Calendar.getInstance().apply {
            set(date.year, date.month - 1, date.day)
        }

        val dayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
        val formattedDate = "${date.year}.${date.month}.${date.day} $dayOfWeek"

        mSelectedDate.value = formattedDate
    }

    fun onMonthChanged(year: Int, month: Int) {
        val firstDay = CalendarDay.from(year, month, 1)
        setSelectedDate(firstDay)
    }


}