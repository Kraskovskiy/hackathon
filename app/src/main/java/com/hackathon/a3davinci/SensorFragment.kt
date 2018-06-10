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


class SensorFragment : Fragment(), SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    val points = mutableListOf<Pair<Float, Float>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {

        val coordinates = event.values
        if (points.isNotEmpty()) {
            val last: Pair<Float, Float> = points.last()
            points.add((last.first + coordinates[0]) to (last.second + coordinates[1]))
        } else {
            points.add(coordinates[0] to coordinates[1])
        }
        //    Log.e("${event.sensor.type} sensors", coordinates.joinToString(", "))
    }

    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager?.unregisterListener(this)
        //  sendPoints(points)
        points.clear()
    }


}

data class CoordinatesHolder(
        val points: Array<Float>
)


fun sendPoints(points: List<Pair<Float, Float>>) {
    val flattenPoints: List<Float> = points.flatMap { it.toList() }
    val gson = Gson()
    val serializedPoints = gson.toJson(flattenPoints)

    Log.e("sendPoint", serializedPoints)
    Log.e("sendPoint", retrievePoints(serializedPoints).toString())
}

fun retrievePoints(p: String): List<Pair<Float, Float>> {
    val points: List<Float> = Gson()
            .fromJson(p, object : TypeToken<List<Float>>() {}.type)
    return points.chunked(2).map { it[1] to it[2] }
}