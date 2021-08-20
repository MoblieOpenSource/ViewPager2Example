package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.myapplication.main_fragment.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mainContainer: FragmentContainerView by lazy { findViewById(R.id.main_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(
                mainContainer.id,
                MainFragment(),
                "home"
            )
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentById(mainContainer.id).let { fragment ->
            if (fragment is UserInteractionHandler && fragment.onBackPressed()) {
                return
            }
        }

        super.onBackPressed()
    }
}