package com.example.mediasession

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

private const val REQUEST_CODE = 1


class MainActivity : AppCompatActivity() {

    var audio: Uri? = null

    var mediaPlayer: MediaPlayer? = null

    private val musicList = mutableSetOf<Music>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Toast.makeText(this, "権限が拒否されました", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE
                )
            }
        } else {
            Toast.makeText(this, "すでに権限があります", Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            val contentResolver = applicationContext.contentResolver
            val cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
            )?.use { cursor ->

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val title =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))

                    musicList += Music(id, title)

                    Log.d("Audio", "id $id")
                    Log.d("Audio", "title $title")

                    val uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    this.audio = uri
                }
            }

            hello.text = musicList.toString()
            val musicAdapter = MusicAdapter(LayoutInflater.from(this)) { music ->
                if (mediaPlayer == null) {

                    val uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        music.id
                    )

                    mediaPlayer = MediaPlayer.create(this, uri).apply {
                        start()
                    }
                } else if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.release()

                    val uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        music.id
                    )

                    mediaPlayer = MediaPlayer.create(this, uri).apply {
                        start()
                    }
                }
            }
            music_list.adapter = musicAdapter
            musicAdapter.submitList(musicList.toList())

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "権限が追加されました", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "拒否されました", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}

data class Music(
    val id: Long,
    val title: String
)