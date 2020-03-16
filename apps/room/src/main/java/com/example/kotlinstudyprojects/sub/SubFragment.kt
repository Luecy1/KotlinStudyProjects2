package com.example.kotlinstudyprojects.sub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinstudyprojects.databinding.SubFragmentBinding
import com.example.kotlinstudyprojects.main.getViewModelFactory

class SubFragment : Fragment() {

    private val viewModel by viewModels<SubViewModel> { getViewModelFactory() }

    private lateinit var binding: SubFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SubFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this

        val subAdapter = SubAdapter(viewModel)
        binding.recyclerView.adapter = subAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.userList.observe(viewLifecycleOwner, Observer {
            subAdapter.submitList(it)
        })

        viewModel.user()
    }

}
