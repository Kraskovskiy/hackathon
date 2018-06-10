package com.hackathon.a3davinci

import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

// SensorEvent

@RuntimePermissions
class CoordinatesFetchPrototype {

    @NeedsPermission(Manifest.permission.SENSORS)
    fun startTrackingDrawing() {

    }

    @NeedsPermission(Manifest.permission.SENSORS)
    fun stopTrackingDrawing() {

    }

    @NeedsPermission(Manifest.permission.SENSORS)
    fun getCoordinatesFromSensors(){

    }


    fun getDrawingCoordinatesReadable(): List<Triple<Double, Double, Double>> {
        return emptyList()
    }
}