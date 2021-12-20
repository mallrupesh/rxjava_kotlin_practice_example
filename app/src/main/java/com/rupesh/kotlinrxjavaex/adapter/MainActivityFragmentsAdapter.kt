package com.rupesh.kotlinrxjavaex.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rupesh.kotlinrxjavaex.view.MovieFragment
import com.rupesh.kotlinrxjavaex.view.WatchListFragment

class MainActivityFragmentsAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    // Returns Fragments count
    override fun getItemCount(): Int {
        return 2
    }

    // Returns Fragment position
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MovieFragment()
            1 -> WatchListFragment()
            else -> MovieFragment()
        }
    }
}