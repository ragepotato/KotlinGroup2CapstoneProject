package com.stephent.kotlin2capstoneproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {return PartFragment("https://www.reddit.com/r/buildapcsales/new/?f=flair_name%3A%22GPU%22")}
            1 -> {return PartFragment("https://www.reddit.com/r/buildapcsales/new/?f=flair_name%3A%22CPU%22")}
            2 -> {return PartFragment("https://www.reddit.com/r/buildapcsales/new/?f=flair_name%3A%22RAM%22")}
            3 -> {return PartFragment("https://www.reddit.com/r/buildapcsales/new/?f=flair_name%3A%22HDD%22")}
            else -> {return PartFragment("https://www.reddit.com/r/buildapcsales/new/?f=flair_name%3A%22GPU%22")}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return "GPU"}
            1 -> {return "CPU"}
            2 -> {return "RAM"}
            3 -> {return "HDD"}
        }

        return super.getPageTitle(position)
    }

}