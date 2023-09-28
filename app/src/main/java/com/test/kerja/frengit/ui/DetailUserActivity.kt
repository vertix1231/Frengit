package com.test.kerja.frengit.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.kerja.frengit.R
import com.test.kerja.frengit.data.response.ItemsItem
import com.test.kerja.frengit.data.response.UserDetail
import com.test.kerja.frengit.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower_tab_text_1,
            R.string.following_tab_text_2
        )
        const val DETAILUSER = "DETAILUSER"
    }
    private val detailMainViewModel by viewModels<DetailMainViewModel>()
    private lateinit var binding:ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        detailMainViewModel.Gitdetailuser.observe(this){
            if (it != null) {
                setDetailUserData(it)
            }
        }
        detailMainViewModel.isLoadingdetail.observe(this){
            showLoading(it)
        }
        setTabNViewpager()
    }
    private fun setTabNViewpager(){
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        // "update" each tab label visibility to force calling TabView.update() under the hood.
        for (i in 0 until binding.tabs.tabCount) {
            binding.tabs.getTabAt(i)?.tabLabelVisibility = TabLayout.TAB_LABEL_VISIBILITY_LABELED
        }
        supportActionBar?.elevation = 0f
        getDetailUserDataFromMain()
    }
    @SuppressLint("SetTextI18n")
    private fun getDetailUserDataFromMain(){
        val receive =intent.getParcelableExtra<ItemsItem>(DETAILUSER) as ItemsItem
        binding.nameDetailTv.text = "${receive.login} id: ${receive.id}"
        Glide.with(this)
            .load(receive.avatar_url)
            .into(binding.ivImagedetail)
        receive.login?.let { detailMainViewModel.findDetailGitUser(it) }
        receive.login?.let { detailMainViewModel.findDetailFollowerGitUser(it) }
    }
    private fun setDetailUserData(receive:UserDetail){
        binding.usernameDetailTv.text = "${receive.name}"
        binding.locationDetailTv.text = "location ${receive.location} follower ${receive.followers}"
        binding.companyDetailTv.text = "company ${receive.company}  following ${receive.following}"
        binding.repositoryDetailTv.text = "${receive.repos_url}"
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}