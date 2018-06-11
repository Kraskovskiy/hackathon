package com.hackathon.a3davinci

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class PictureDrawer: SurfaceView, SurfaceHolder.Callback {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }

    init {
        holder.addCallback(this)
    }

    fun setData(points: List<Pair<Float, Float>>) {
        val drawThread = DrawThread(points, this.holder)
        drawThread.start()
    }


}

class DrawThread(private val points: List<Pair<Float, Float>>, private val surfaceHolder: SurfaceHolder) : Thread() {
    override fun run() {
        try {
            val canvas = surfaceHolder.lockCanvas(null)
            val paint = Paint()
            paint.color = Color.WHITE
            synchronized(surfaceHolder) {
                points.zipWithNext().map {
                    arrayOf(it.first.first, it.first.second, it.first.first + it.second.first,
                            it.first.second + it.second.second)
                }.forEach {
                    canvas.drawCircle(it[0]*-1+500, it[1]*-1+500, 35f,paint)
                    canvas.drawCircle(it[2]*-1+500, it[3]*-1+500, 35f,paint)
                }
                surfaceHolder.unlockCanvasAndPost(canvas)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}