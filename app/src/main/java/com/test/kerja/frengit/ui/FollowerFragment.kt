package com.test.kerja.frengit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.kerja.frengit.data.response.TabUserDetail
import com.test.kerja.frengit.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private val detailMainViewModel by viewModels<DetailMainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListRv()
        detailMainViewModel.detailFollowerGituser.observe(viewLifecycleOwner){
            setUserFollower(it)
        }
        detailMainViewModel.isLoadingfollower.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun showListRv() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollower.layoutManager = layoutManager
        binding.rvFollower.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)
    }
    private fun setUserFollower(rc: List<TabUserDetail>?) {
        val adapter = TabDetailAdapter()
        adapter.submitList(rc)
        binding.rvFollower.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFollower.visibility = View.VISIBLE
        } else {
            binding.progressBarFollower.visibility = View.GONE
        }
    }
}