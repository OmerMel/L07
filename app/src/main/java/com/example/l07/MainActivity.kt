package com.example.l07

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.l07.interfraces.TiltCallback
import com.example.l07.utilities.BackgroundMusicPlayer
import com.example.l07.utilities.SingleSoundPlayer
import com.example.l07.utilities.TiltDetector
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {


    private lateinit var sensors_LBL_tilt_x : MaterialTextView

    private lateinit var sensors_LBL_tilt_y : MaterialTextView

    private lateinit var sensors_BTN_boom: MaterialButton

    private lateinit var tiltDetector: TiltDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        initViews()
        initTiltDetector()
    }

    override fun onResume() {
        super.onResume()
        tiltDetector.start()
        BackgroundMusicPlayer.getInstance().playMusic()
    }

    override fun onPause() {
        super.onPause()
        tiltDetector.stop()
        BackgroundMusicPlayer.getInstance().pauseMusic()
    }

    private fun findViews() {
        sensors_LBL_tilt_x = findViewById(R.id.sensors_LBL_tilt_x)
        sensors_LBL_tilt_y = findViewById(R.id.sensors_LBL_tilt_y)
        sensors_BTN_boom = findViewById(R.id.sensors_BTN_boom)
    }

    private fun initViews() {
        sensors_BTN_boom.setOnClickListener{
            var ssp: SingleSoundPlayer = SingleSoundPlayer(this)
            ssp.playSound(R.raw.boom)
        }
    }

    private fun initTiltDetector() {
        tiltDetector = TiltDetector(
            context = this,
            tiltCallback = object : TiltCallback {
                override fun tiltX() {
                    tiltDetector.tiltCounterX.toString().also { sensors_LBL_tilt_x.text = it }
                }
                override fun tiltY() {
                    tiltDetector.tiltCounterY.toString().also { sensors_LBL_tilt_y.text = it }
                }
            }
        )
    }


}