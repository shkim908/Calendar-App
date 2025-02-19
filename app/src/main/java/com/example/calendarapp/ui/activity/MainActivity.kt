package com.example.calendarapp.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calendarapp.R
import com.example.calendarapp.ui.material.CalendarDecorators
import com.google.android.material.datepicker.DayViewDecorator
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView(){
        val calendarView: MaterialCalendarView = findViewById(R.id.calendar_view)

        calendarView.addDecorators(CalendarDecorators.saturdayDecorator(applicationContext))
        calendarView.addDecorators(CalendarDecorators.sundayDecorator(applicationContext))

    }

}