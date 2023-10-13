package com.test.kerja.frengit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.kerja.frengit.adapter.DetailGithubAdapter
import com.test.kerja.frengit.adapter.TabDetailAdapter
import com.test.kerja.frengit.model.TabUserDetail
import com.test.kerja.frengit.databinding.FragmentFollowerBinding
import com.test.kerja.frengit.viewmodel.DetailMainViewModel

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private val detailMainViewModel by viewModels<DetailMainViewModel>()
    private var list: ArrayList<TabUserDetail> = arrayListOf()
    private lateinit var adapter: DetailGithubAdapter
    private var found = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)

        Log.i("FollowerFragmentononResume", "onDetailPassSearchName: follower fragment terbentu onResume")
        detailMainViewModel.detailFollowerGituser.observe(viewLifecycleOwner) {
            println("=============================================================================================${it}")
            Log.d(
                "FollowerFragmentinmodelonCreateViewfregit",
                "RvFollowerFragmentinmodelonCreateViewfrengit: $it"
            )
            if (it != null) {
                if (it.isNotEmpty()) {
//                    Log.i("FollowerFragmentinmodel", "showListRvFollowerFragmentinmodel: $it")
                    list.addAll(it)
                    adapter.notifyDataSetChanged()
                }
            }
            showListRv(list)
        }
        detailMainViewModel.isLoadingfollower.observe(viewLifecycleOwner){
            showLoading(it)
        }
        return binding.root
    }
    private fun showData(){
        binding.rvFollower.setHasFixedSize(true)
        binding.rvFollower.layoutManager = LinearLayoutManager(requireContext())
        adapter = DetailGithubAdapter(list)
        binding.rvFollower.adapter = adapter
        if (found) binding.rvFollower.visibility = View.VISIBLE else binding.rvFollower.visibility = View.GONE
    }
    private fun showListRv(rc: List<TabUserDetail>?) {
        binding.rvFollower.layoutManager = LinearLayoutManager(requireContext())
        val itemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.rvFollower.addItemDecoration(itemDecoration)
        val adapter = TabDetailAdapter()
        binding.rvFollower.setHasFixedSize(true)
        Log.i("FollowerFragment", "showListRvFollowerFragment: $rc")
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