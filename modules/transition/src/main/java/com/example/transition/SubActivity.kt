package com.example.transition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sub.*

const val TAG_NAME = "NAME"
const val TAG_IMAGE_URL = "IMAGE_URL"

const val VIEW_NAME_IMAGE = "detail:image";

class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val name = intent.getStringExtra(TAG_NAME)
        val imageUrl = intent.getStringExtra(TAG_IMAGE_URL)

        ViewCompat.setTransitionName(imageView2, VIEW_NAME_IMAGE)

        title = name

        Picasso.with(this)
            .load(imageUrl)
            .noFade()
            .into(imageView2)
    }
}
