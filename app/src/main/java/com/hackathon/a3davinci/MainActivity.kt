package com.hackathon.a3davinci

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val fragment = SensorFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment).commit()

        val points = mutableListOf<Pair<Float, Float>>()
        for (i in 0..100){
            points.add(i.toFloat() to i.toFloat())
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            sendPoints(points)
        }

        val points = mutableListOf<Pair<Float, Float>>()

//        for (i in 0..500 step 10) {
//            points.add(i.toFloat() to i.toFloat())
//        }

        val pictureDrawer = PictureDrawer(this, points)
        setContentView(pictureDrawer)
    }

}
