package com.example.myapplication.main_fragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private var items: MutableList<ViewPagerItem> = mutableListOf()

    override fun createFragment(position: Int): Fragment {
        return items[position].fragment
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].index.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return items.findLast { c -> c.index.toLong() == itemId } != null
    }

    fun add(index: Int, fragment: Fragment) {
        items.add(ViewPagerItem(index, fragment))

        sortItems()
    }

    private fun sortItems() {
        if (items.size > 1) {
            items.sortBy { item -> item.index }
        }
    }
}

data class ViewPagerItem(val index: Int, val fragment: Fragment)