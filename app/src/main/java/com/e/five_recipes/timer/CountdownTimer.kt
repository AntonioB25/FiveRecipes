package com.e.five_recipes.timer

import android.os.CountDownTimer

class CountdownTimer {

   fun test(){
       val timer = object : CountDownTimer(30000, 1000) {

           override fun onTick(millisUntilFinished: Long) {
               millisUntilFinished / 1000
           }

           override fun onFinish() {

           }
       }
       timer.start()
   }


}

