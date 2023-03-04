package com.mintu.assignment5paisa.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mintu.assignment5paisa.WatchListsModel
import com.mintu.assignment5paisa.fragments.TabsFragment

class TabsAdapter(private val tabs: WatchListsModel, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return TabsFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs.MWName[position].MwatchName.toString()
    }

    override fun getCount(): Int {
        return tabs.MWName.size
    }
}