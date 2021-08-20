package com.example.myapplication.widgets.web_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebView
import androidx.viewpager2.widget.ViewPager2

class CustomWebView : WebView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var viewPager: ViewPager2? = null

    fun setViewPager(viewPager2: ViewPager2) {
        this.viewPager = viewPager2
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        //Enables ViewPager when scroll reaches end.
        viewPager?.isUserInputEnabled = true
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        //disables ViewPager when user presses down
        if (event.action == MotionEvent.ACTION_DOWN) {
            viewPager?.isUserInputEnabled = false
            return true
        } else if (event.action == MotionEvent.ACTION_UP) {
            viewPager?.isUserInputEnabled = true
            return true
        }

        return true
    }
}