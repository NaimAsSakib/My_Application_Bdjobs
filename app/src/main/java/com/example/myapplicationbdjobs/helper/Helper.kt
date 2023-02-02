package com.example.myapplicationbdjobs.helper

class Helper {
    companion object{
        fun minuteToTime(minute: Int): String {
            var minute = minute
            var hour = minute / 60
            minute %= 60
            var p = "AM"
            if (hour >= 12) {
                hour %= 12
                p = "PM"
            }
            if (hour == 0) {
                hour = 12
            }
            return (if (hour < 10) "$hour" else hour).toString() + "h " + (if (minute < 10) "$minute" else minute) +"m"
        }
    }
}