package com.example.kotlinstudyprojects.cancel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinstudyprojects.databinding.CancelFragmentBinding

class CancelFragment : Fragment() {

    private val viewModel by viewModels<CancelViewModel>()

    private lateinit var binding: CancelFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CancelFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonProcess.setOnClickListener {
            viewModel.longTimeProcess()
        }
    }

}
