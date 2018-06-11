package com.hackathon.a3davinci

import android.content.Context
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_GYROSCOPE
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SensorFragment(val mContext: Context) : SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    val points = mutableListOf<Pair<Float, Float>>()
    var isActive = false


    init {
        mSensorManager = mContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.e("sensor", "TRIGGERED")
        if (isActive) {
            val coordinates = event.values
            if (points.isNotEmpty()) {
                val last: Pair<Float, Float> = points.last()
                points.add((last.first - coordinates[2]) to (last.second + coordinates[0]))
            } else {
                points.add(-coordinates[2] to coordinates[0])
            }
            Log.e("${event.sensor.type} sensors", points.toString())
        }
    }

    fun sendAndClear() {
        sendPoints(points)
        points.clear()
    }
}

fun sendPoints(points: List<Pair<Float, Float>>) {
    val flattenPoints: List<Float> = points.flatMap { it.toList() }
    val gson = Gson()
    val serializedPoints = gson.toJson(flattenPoints)
}

fun retrievePoints(p: String): List<Pair<Float, Float>> {
    val points: List<Float> = Gson()
            .fromJson(p, object : TypeToken<List<Float>>() {}.type)
    return points.chunked(2).map { it[0] to it[1] }
}

