package com.example.kotlinstudyprojects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class BlankFragment : Fragment() {

    private val viewModel by viewModels<BlankViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blank_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userDao.getAll().observe(viewLifecycleOwner, Observer { users ->
            for (user in users) {
                Log.d("Fragment", "user$user")
            }
        })
    }
}

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val roomDatabase = (requireContext().applicationContext as RoomApplication).roomDatabase
    return ViewModelFactory(roomDatabase, this)
}