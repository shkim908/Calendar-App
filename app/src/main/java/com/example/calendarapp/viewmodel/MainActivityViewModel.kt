package com.example.calendarapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivityViewModel : ViewModel() {
    private val mSelectedDate = MutableLiveData<String>()  // 선택된 날짜 저장
    val selectedDate: LiveData<String> get() = mSelectedDate  // 외부에서 읽기만 가능

    fun setSelectedDate(date: CalendarDay) {
        val calendar = Calendar.getInstance().apply {
            set(date.year, date.month - 1, date.day) // month는 0부터 시작하므로 -1
        }

        val dayOfWeek = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time) // 요일 가져오기 (Mon, Tue 등)
        val formattedDate = "${date.year}.${date.month}.${date.day} $dayOfWeek"

        mSelectedDate.value = formattedDate
    }

    fun onMonthChanged(year: Int, month: Int) {
        val firstDay = CalendarDay.from(year, month, 1) // 새 달의 1일
        setSelectedDate(firstDay) // 자동으로 1일을 선택
    }


}