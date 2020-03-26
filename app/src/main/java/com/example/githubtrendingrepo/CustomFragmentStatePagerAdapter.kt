package com.example.githubtrendingrepo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.githubtrendingrepo.views.homepage.GithubDevelopersFragment
import com.example.githubtrendingrepo.views.homepage.GithubRepoFragment

class CustomFragmentStatePagerAdapter(fm :FragmentManager) :FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        when(position)
        {
            0 -> return GithubRepoFragment()

            1 -> return GithubDevelopersFragment()

            else -> return GithubRepoFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}