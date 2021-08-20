package com.example.myapplication.widgets.view_pager

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/*
Fix scroll problem
-----------------------------------------------------------------------
When you try to scroll down your page, you get a lag.
Sometimes, you will be thrown into the second page or the first page.
This is a known problem for ViewPager2 currently.
 */
fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * 2) // "2" was obtained experimentally
}