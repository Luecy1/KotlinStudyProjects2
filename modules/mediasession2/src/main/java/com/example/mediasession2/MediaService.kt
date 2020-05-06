package com.example.mediasession2

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import android.widget.Toast
import androidx.media.MediaBrowserServiceCompat

private val ROOT_TAG = "root"

class MediaService : MediaBrowserServiceCompat() {

    private lateinit var mediaSession: MediaSessionCompat

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSessionCompat(applicationContext, "TAG").apply {
            setCallback(callback)
        }

        sessionToken = mediaSession.sessionToken
    }

    private val callback = object : MediaSessionCompat.Callback() {
        override fun onPlay() {
            super.onPlay()
            Toast.makeText(this@MediaService, "再生が要求されました", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        if (parentId == ROOT_TAG) {
            result.sendResult(MusicLibrary.getMediaItem())
        } else {
            result.sendResult(mutableListOf())
        }
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        Log.d(
            "MediaService", "Connected from pkg:$clientPackageName uid:$clientUid"
        )
        return BrowserRoot(ROOT_TAG, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "終了します", Toast.LENGTH_SHORT).show()
        mediaSession.isActive = false
        mediaSession.release()
    }
}