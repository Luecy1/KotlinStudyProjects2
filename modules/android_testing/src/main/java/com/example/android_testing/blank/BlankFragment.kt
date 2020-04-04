package com.example.android_testing.blank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_testing.R
import com.example.android_testing.ViewModelFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject


class BlankFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<BlankViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Timber.d(viewModel.toString())
    }
}
