package com.example.myapplication.main_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.UserInteractionHandler
import com.example.myapplication.pages.CustomFragment
import com.example.myapplication.pages.HomeFragment
import com.example.myapplication.widgets.view_pager.ZoomOutPageTransformer
import com.example.myapplication.widgets.view_pager.reduceDragSensitivity
import kotlin.random.Random

class MainFragment : Fragment(R.layout.fragment_main), UserInteractionHandler {
    companion object {
        private const val HOME_PAGE = 1
    }

    private var adapter: HomeViewPagerAdapter? = null

    private val frameHome: FrameLayout by lazy { requireView().findViewById(R.id.frameHome) }
    val viewPager: ViewPager2 by lazy { requireView().findViewById(R.id.view_pager) }

    private var activeFragmentIndex: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeViewPagerAdapter(this).also {
            with(viewPager) {
                reduceDragSensitivity()
                setPageTransformer(ZoomOutPageTransformer())
                isSaveEnabled = false
                adapter = it
            }
        }.apply {
            add(HOME_PAGE, HomeFragment())
        }

        adapter?.let { adapter ->
            adapter.add(0, CustomFragment.newInstance("https://www.etq-amsterdam.com"))
            adapter.add(2, CustomFragment.newInstance("https://play.google.com/store/apps"))

            viewPager.registerOnPageChangeCallback(onPageScrollStateChanged)
            viewPager.offscreenPageLimit = adapter.itemCount

            activeFragmentIndex = activeFragmentIndex ?: HOME_PAGE
            activeFragmentIndex?.let {
                viewPager.setCurrentItem(it, false)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        adapter?.let {
            return if (it.itemCount > 1 && activeFragmentIndex != HOME_PAGE) {
                viewPager.setCurrentItem(HOME_PAGE, true)

                true
            } else {
                false
            }
        } ?: return false
    }

    //region ViewPager -> onPageScrollStateChanged + Transformer + ...
    private var onPageScrollStateChanged = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            activeFragmentIndex = position
        }

        override fun onPageScrollStateChanged(state: Int) {
            if (state == 1) {
                frameHome.setBackgroundColor(generateRandomColor())
            }
        }
    }

    private fun generateRandomColor() = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    //endregion
}