package com.example.calendarapp.ui.material


import android.content.Context
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.example.calendarapp.R
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

object CalendarDecorators {
    fun sundayDecorator(context: Context): DayViewDecorator {
        return object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                val calendar = Calendar.getInstance()
                calendar.set(day.year, day.month - 1, day.day)
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            context,
                            R.color.calender_color_red
                        )
                    )
                )
            }
        }
    }

    fun saturdayDecorator(context: Context): DayViewDecorator {
        return object : DayViewDecorator {
            override fun shouldDecorate(day: CalendarDay): Boolean {
                val calendar = Calendar.getInstance()
                calendar.set(day.year, day.month - 1, day.day)
                return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
            }

            override fun decorate(view: DayViewFacade) {
                view.addSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            context,
                            R.color.calender_color_blue
                        )
                    )
                )
            }
        }
    }

}


