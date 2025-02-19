package com.example.calendarapp.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapp.R
import com.example.calendarapp.databinding.ActivityMainBinding
import com.example.calendarapp.ui.adapter.ScheduleAdapter
import com.example.calendarapp.ui.material.CalendarDecorators
import com.example.calendarapp.ui.popup.AddScheduleFragment
import com.example.calendarapp.ui.widget.ScheduleList
import com.example.calendarapp.viewmodel.MainActivityViewModel
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var mSelectedDayDecorator: DayViewDecorator
    private lateinit var mTodayDecorator: DayViewDecorator
    private lateinit var mMonthDecorator: DayViewDecorator
    private lateinit var mSundayDecorator: DayViewDecorator
    private lateinit var mSaturdayDecorator: DayViewDecorator


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initList()
        initBtn()
        viewModelObservable()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() = with(binding){
        with(calendarView) {
            mSelectedDayDecorator = CalendarDecorators.selectedDayDecorator(applicationContext, R.drawable.calendar_selector)
            mTodayDecorator = CalendarDecorators.todayDecorator(applicationContext)
            mSundayDecorator = CalendarDecorators.sundayDecorator(applicationContext)
            mSaturdayDecorator = CalendarDecorators.saturdayDecorator(applicationContext)
            mMonthDecorator = CalendarDecorators.monthDecorator(
                applicationContext,
                CalendarDay.today().month)

            addDecorators(
                mSelectedDayDecorator,
                mTodayDecorator,
                mSundayDecorator,
                mSaturdayDecorator,
                mMonthDecorator
            )

            setOnMonthChangedListener { widget, date ->
                widget.clearSelection()
                removeDecorators()
                invalidateDecorators()
                mMonthDecorator =
                    CalendarDecorators.monthDecorator(applicationContext, date.month)
                addDecorators(
                    mSelectedDayDecorator,
                    mTodayDecorator,
                    mSundayDecorator,
                    mSaturdayDecorator,
                    mMonthDecorator
                )
                val today = LocalDate.now()
                val clickedDay = if (date.toLocalDate().month == today.month)
                    CalendarDay.from(today.year, today.monthValue, today.dayOfMonth)
                else
                    CalendarDay.from(date.year, date.month, 1)

                widget.setSelectedDate(clickedDay)
                viewModel.setSelectedDate(clickedDay)

            }

            setOnDateChangedListener { _, date, _ ->
                viewModel.setSelectedDate(date)
            }
        }



    }

    private fun initList(){
        val scheduleList = ArrayList<ScheduleList>().apply {
            add(ScheduleList(ContextCompat.getColor(applicationContext, R.color.calender_color_red), "Meeting", "10:00 AM"))
            add(ScheduleList(ContextCompat.getColor(applicationContext, R.color.calender_color_orange), "Lunch", "12:00 PM"))
            add(ScheduleList(ContextCompat.getColor(applicationContext, R.color.calender_color_blue), "Conference", "2:00 PM"))
        }

        binding.recyclerViewSchedule.adapter = ScheduleAdapter(scheduleList)
    }

    private fun initBtn(){
        binding.fbRegisterSchedule.setOnClickListener {
            val fragment = AddScheduleFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

    private fun viewModelObservable() {
        viewModel.selectedDate.observe(this) { date ->
            binding.calendarTv.text = date
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun CalendarDay.toLocalDate(): LocalDate {
        return LocalDate.of(year, month, day)
    }





}