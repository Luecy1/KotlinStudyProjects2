package com.example.slideshow

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private val resources = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    private var position = 0
    private var isSlideShow = false
    private val handler = Handler()

    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSwicher.setFactory {
            ImageView(this)
        }

        imageSwicher.setImageResource(resources[0])

        prevButton.setOnClickListener {
            imageSwicher.setInAnimation(this, android.R.anim.fade_in)
            imageSwicher.setOutAnimation(this, android.R.anim.fade_out)
            movePosition(-1)
        }

        nextButton.setOnClickListener {
            imageSwicher.setInAnimation(this, android.R.anim.slide_in_left)
            imageSwicher.setOutAnimation(this, android.R.anim.slide_out_right)
            movePosition(1)
        }

        timer(period = 5000) {
            handler.post {
                if (isSlideShow) {
                    movePosition(1)
                }
            }
        }

        slideshowButton.setOnClickListener {
            isSlideShow = !isSlideShow

            when (isSlideShow) {
                true -> player.start()
                false -> player.apply {
                    pause()
                    seekTo(0)
                }
            }
        }

        player = MediaPlayer.create(this, R.raw.bgm)
        player.isLooping = true
    }

    private fun movePosition(move: Int) {
        position += move
        if (position >= resources.size) {
            position = 0
        } else if (position < 0) {
            position = resources.size - 1
        }

        imageSwicher.setImageResource(resources[position])
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
