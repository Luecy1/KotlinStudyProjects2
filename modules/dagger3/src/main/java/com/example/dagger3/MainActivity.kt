package com.example.dagger3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dagger3.di.DaggerAppComponent
import com.example.dagger3.ui.main.MainFragment
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
