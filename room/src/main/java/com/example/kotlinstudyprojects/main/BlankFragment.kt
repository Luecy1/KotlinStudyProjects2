package com.example.kotlinstudyprojects.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kotlinstudyprojects.R
import com.example.kotlinstudyprojects.RoomApplication
import com.example.kotlinstudyprojects.ViewModelFactory
import com.example.kotlinstudyprojects.databinding.BlankFragmentBinding

class BlankFragment : Fragment() {

    private val viewModel by viewModels<BlankViewModel> { getViewModelFactory() }

    private lateinit var binding: BlankFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BlankFragmentBinding.inflate(layoutInflater, container, false)

        binding.viewmodel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.userDao.getAll().observe(viewLifecycleOwner, Observer { users ->
            for (user in users) {
                Log.d("Room", "user$user")
            }
        })

        binding.buttonMove.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_subFragment)
        }
    }
}

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val roomDatabase = (requireContext().applicationContext as RoomApplication).roomDatabase
    return ViewModelFactory(roomDatabase, this)
}