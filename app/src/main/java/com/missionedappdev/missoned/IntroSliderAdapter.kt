package com.missionedappdev.missoned

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IntroSliderAdapter(private val introSlides: List<IntroSlide>): RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return introSlides.size

    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    inner class IntroSliderViewHolder(view:View): RecyclerView.ViewHolder(view){
        private val texttitle = view.findViewById<TextView>(R.id.text_title)
        private val textdescription = view.findViewById<TextView>(R.id.text_settings)
        private val imageicon = view.findViewById<ImageView>(R.id.imageslideicon)
        fun bind(introslide:IntroSlide){
            texttitle.text= introslide.title
            textdescription.text = introslide.description
            imageicon.setImageResource(introslide.icon)
        }

    }
}