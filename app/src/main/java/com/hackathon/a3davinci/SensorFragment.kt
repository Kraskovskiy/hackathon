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


class SensorFragment : Fragment(), SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mGyroscope: Sensor? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager!!.getDefaultSensor(TYPE_GYROSCOPE);
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        // The light sensor returns a single value.
        // Many sensors return 3 values, one for each axis.
//        val lux = event.values[0]
        val coordinates = event.values

        Log.e("${event.sensor.type} sensors", coordinates.joinToString(", "))
        // Do something with this sensor value.
    }

}
