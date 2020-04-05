package com.example.android_testing.blank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android_testing.R
import com.example.android_testing.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.blank_fragment.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d(viewModel.toString())

        viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->
            val userString = userList.joinToString("\n") {
                it.toString()
            }

            textview.text = userString
        })
    }
}
