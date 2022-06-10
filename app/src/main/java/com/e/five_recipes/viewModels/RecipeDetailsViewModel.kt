package com.e.five_recipes.viewModels

import android.content.Context
import android.graphics.drawable.Animatable
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.five_recipes.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RecipeDetailsViewModel(

) : ViewModel() {

    val _timer = MutableStateFlow<String?>(null)
    val _timerTitle = MutableStateFlow<String>("Timer")
//    val _animeFloat = { Animatable(0f) }
//
//
//
//    fun setAnimeFloat(value: Float){
//        viewModelScope.launch {
//            _animeFloat.emit(value)
//        }
//    }

    fun setTimerTitle(title: String) {
        viewModelScope.launch {
            _timerTitle.emit(title)
        }
    }

    fun setTimer(timer: String) {
        viewModelScope.launch {
            _timer.emit(timer)
        }
    }

    private lateinit var timer: CountDownTimer
    private var milliLeft: Long = 0

    fun startTimer(timeLengthMilli: Long, context: Context) {
        timer = object : CountDownTimer(timeLengthMilli, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val str = String.format(
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(
                                    millisUntilFinished
                                )
                            )
                );

                setTimer(str)
                milliLeft = millisUntilFinished
            }

            override fun onFinish() {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                val mMediaPlayer = MediaPlayer.create(context, R.raw.timer_sound)
                setTimer("done")


                mMediaPlayer.start()
            }
        }
        timer.start()
    }

    fun timerPause() {
        timer.cancel()
    }

    fun timerResume(context: Context) {
        startTimer(milliLeft, context)
    }

}

