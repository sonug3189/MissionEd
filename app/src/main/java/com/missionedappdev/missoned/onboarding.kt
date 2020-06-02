package com.missionedappdev.missoned

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboarding.*

class onboarding : AppCompatActivity() {

    private val introsliderAdapter = IntroSliderAdapter(
            listOf(
                    IntroSlide(
                            "Test your skills and improve.",
                            "By taking Quizes all at your comfort !",
                            R.drawable.onboarding1
                    ),
                    IntroSlide(
                            "Never miss another Lecture.",
                            "We keep uou updated all the time!",
                            R.drawable.onboarding2
                    ),
                    IntroSlide(
                            "Personalized Doubt Clearing.",
                            "Complete TA support. Join the community now!",
                            R.drawable.onboarding3
                    )
            )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        introSliderViewpager.adapter = introsliderAdapter

        next_intro.setOnClickListener {
            if(introSliderViewpager.currentItem + 1 < introsliderAdapter.itemCount){
                introSliderViewpager.currentItem += 1
            }else{
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }

            }
        }
        skip_intro.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()

            }
        }

    }


}
