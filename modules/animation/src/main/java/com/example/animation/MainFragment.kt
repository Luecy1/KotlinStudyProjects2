package com.example.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val icon = AnimatedVectorDrawableCompat.create(
            requireContext(),
            R.drawable.ic_1099_ar_h
        )!!

        icon.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
        })

        imageView.setImageDrawable(icon)

        imageView.setOnClickListener {
            icon.start()
        }

        icon.start()
    }
}
