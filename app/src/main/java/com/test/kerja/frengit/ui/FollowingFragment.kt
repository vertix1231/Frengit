package com.test.kerja.frengit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.kerja.frengit.adapter.TabDetailAdapter
import com.test.kerja.frengit.model.TabUserDetail
import com.test.kerja.frengit.databinding.FragmentFollowingBinding
import com.test.kerja.frengit.viewmodel.DetailMainViewModel

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val detailMainViewModel by viewModels<DetailMainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListRv()
        detailMainViewModel.detailFollowingGituser.observe(viewLifecycleOwner) {
            setUserFollowing(it)
        }
        detailMainViewModel.isLoadingfollowing.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun showListRv() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollowing.layoutManager = layoutManager
        binding.rvFollowing.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)
    }

    private fun setUserFollowing(rc: List<TabUserDetail>?) {
        val adapter = TabDetailAdapter()
        adapter.submitList(rc)
        binding.rvFollowing.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFollowing.visibility = View.VISIBLE
        } else {
            binding.progressBarFollowing.visibility = View.GONE
        }
    }
}