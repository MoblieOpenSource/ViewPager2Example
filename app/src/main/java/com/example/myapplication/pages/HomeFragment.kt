package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val rvHorizontal: RecyclerView by lazy { requireView().findViewById(R.id.rv_horizontal) }
    private val rvVertical: RecyclerView by lazy { requireView().findViewById(R.id.rv_vertical) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvHorizontal.setUpRecyclerView(RecyclerView.HORIZONTAL)
        rvVertical.setUpRecyclerView(RecyclerView.VERTICAL)
    }

    private fun RecyclerView.setUpRecyclerView(orientation: Int) {
        layoutManager = LinearLayoutManager(context, orientation, false)
        adapter = RvAdapter(orientation)
    }
}

class RvAdapter(private val orientation: Int) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return 40
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val tv = TextView(parent.context)
        tv.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT).apply {
            if (orientation == RecyclerView.HORIZONTAL) {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
        tv.textSize = 20f
        tv.gravity = Gravity.CENTER
        tv.setPadding(20, 55, 20, 55)
        return ViewHolder(tv)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            tv.text = "Item $adapterPosition"
            tv.setBackgroundResource(cellColors[position % cellColors.size])
        }
    }

    class ViewHolder(val tv: TextView) : RecyclerView.ViewHolder(tv)

    private val cellColors = listOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_green_light
    )
}