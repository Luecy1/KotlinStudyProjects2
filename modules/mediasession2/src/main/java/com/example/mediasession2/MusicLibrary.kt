package com.example.mediasession2

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import android.util.Log

object MusicLibrary {

    fun getAssetsFile(context: Context): AssetFileDescriptor {
        return context.resources.openRawResourceFd(R.raw.bgm)
    }

    fun getMetadata(context: Context): MediaMetadataCompat {
        val retriever = MediaMetadataRetriever().apply {
            val afd = getAssetsFile(context)
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        }
        val meta = MediaMetadataCompat.Builder()
            .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, "sample.mp3")
            .putString(
                MediaMetadataCompat.METADATA_KEY_TITLE,
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            )
            .putString(
                MediaMetadataCompat.METADATA_KEY_ARTIST,
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            )
            .putBitmap(
                MediaMetadataCompat.METADATA_KEY_ART,
                createArt(retriever)
            )
            .build()
        retriever.release()
        return meta
    }

    private fun createArt(retriever: MediaMetadataRetriever): Bitmap? {
        return try {
            val pic = retriever.embeddedPicture
            BitmapFactory.decodeByteArray(pic, 0, pic.size)
        } catch (e: Exception) {
            Log.d("MusicLibrary", "Error", e)
            null
        }
    }

    fun getMediaItem(): MutableList<MediaBrowserCompat.MediaItem> {
        val result: MutableList<MediaBrowserCompat.MediaItem> = mutableListOf()

        val mediaDescription = MediaDescriptionCompat.Builder()
            .setMediaId("0")
            .setDescription("hoge")
            .build()

        result.add(
            MediaBrowserCompat.MediaItem(
                mediaDescription,
                MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
            )
        )
        return result
    }
}