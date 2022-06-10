package com.e.five_recipes.timer

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder

class TimerService: Service() {
    override fun onBind(p0: Intent?): IBinder? = null


    val timer = object : CountDownTimer(30000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            millisUntilFinished / 1000
        }

        override fun onFinish() {

        }
    }

}
