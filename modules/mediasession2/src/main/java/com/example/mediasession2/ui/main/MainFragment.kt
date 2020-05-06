package com.example.mediasession2.ui.main

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mediasession2.MediaService
import com.example.mediasession2.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    lateinit var browser: MediaBrowserCompat
    lateinit var controller: MediaControllerCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playButton.setOnClickListener {
            controller.transportControls.play()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.startService(Intent(activity, MediaService::class.java))

        // MediaBrowser
        browser = MediaBrowserCompat(
            requireContext(),
            ComponentName(requireContext(), MediaService::class.java),
            connectionCallback,
            null
        )
        browser.connect()
    }

    private val connectionCallback = object : MediaBrowserCompat.ConnectionCallback() {
        override fun onConnected() {
            super.onConnected()

            controller = MediaControllerCompat(requireContext(), browser.sessionToken)

            browser.subscribe(browser.root, searchCallback)

            Toast.makeText(requireContext(), "接続しました", Toast.LENGTH_SHORT).show()
        }
    }

    private val searchCallback = object : MediaBrowserCompat.SubscriptionCallback() {

        override fun onChildrenLoaded(
            parentId: String,
            children: MutableList<MediaBrowserCompat.MediaItem>
        ) {
            super.onChildrenLoaded(parentId, children)
            Log.d("hoge", children.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        browser.disconnect()
    }
}
