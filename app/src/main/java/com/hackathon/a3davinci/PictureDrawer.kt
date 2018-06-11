package com.hackathon.a3davinci

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class PictureDrawer(context: Context?, val points: List<Pair<Float, Float>>) :
        SurfaceView(context), SurfaceHolder.Callback {

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        val drawThread = DrawThread(this.points, this.holder)
        drawThread.start()
    }

    init {
        holder.addCallback(this)
    }


}

class DrawThread(private val points: List<Pair<Float, Float>>, private val surfaceHolder: SurfaceHolder) : Thread() {
    override fun run() {
        try {
            val canvas = surfaceHolder.lockCanvas(null)
            val paint = Paint()
            paint.color = Color.BLUE
            synchronized(surfaceHolder) {
                points.zipWithNext().map {
                    arrayOf(it.first.first, it.first.second, it.first.first + it.second.first,
                            it.first.second + it.second.second)
                }.forEach {
                    canvas.drawLine(it[0], it[1], it[2], it[3], paint)
                }
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}