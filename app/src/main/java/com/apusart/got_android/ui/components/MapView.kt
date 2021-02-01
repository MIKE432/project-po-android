package com.apusart.got_android.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.apusart.got_android.R
import com.apusart.got_android.api.models.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class MapView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val painterInner: Paint = Paint()
    private val painterOuter: Paint = Paint()
    private var mWidth = 0
    private var mHeight = 0

    var segments = listOf<Segment>()
        set(value) {
            field = value
            invalidate()
        }

    init {
        background = ResourcesCompat.getDrawable(resources, R.drawable.map_example, null)
        painterInner.color = ResourcesCompat.getColor(resources, R.color.primary_900, null)
        painterInner.strokeWidth = 4.0f
        painterOuter.style = Paint.Style.FILL_AND_STROKE
        painterOuter.color = ResourcesCompat.getColor(resources, R.color.text_green, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            segments.forEach { drawSegment(this, it) }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = right - left
        mHeight = bottom - top
    }

    private fun drawPathPoint(canvas: Canvas?, x: Double, y: Double) {
        canvas?.apply {
            drawCircle(y.toFloat(), x.toFloat(), 15f, painterOuter)
            drawCircle(y.toFloat(), x.toFloat(), 12f, painterInner)
        }
    }

    private fun drawSegment(canvas: Canvas?, activeSegment: Segment) {
        val startX = (((activeSegment.poczatek.szerokoscGeo - 49.221295)/(49.294686 - 49.221295)) * mHeight)/1.1 + 2
        val startY = (((activeSegment.poczatek.dlugoscGeo - 19.906059)/(20.005456 - 19.906059)) * mWidth)/1.1 + 15

        val endX = (((activeSegment.koniec.szerokoscGeo - 49.221295)/(49.294686 - 49.221295)) * mHeight)/1.1 + 2
        val endY = (((activeSegment.koniec.dlugoscGeo - 19.906059)/(20.005456 - 19.906059)) * mWidth)/1.1 + 15

        var currentX = startX
        var currentY = startY

        val step = calculateStep(startX, startY, endX, endY)
        val stepX = step.a
        val stepY = step.b

        canvas?.apply {
            while (!(abs(currentX - endX) < 20 && abs(currentY - endY) < 20)) {
                drawPathPoint(this, currentX, currentY)

                currentX += stepX
                currentY += stepY
            }
        }
    }

    private fun calculateStep(
        startX: Double,
        startY: Double,
        endX: Double,
        endY: Double
    ): Touple<Double, Double> {
        val x = abs(endX - startX)
        val y = abs(endY - startY)
        val longArm = sqrt(x.pow(2) + y.pow(2))
        val ratio = 30 / longArm

        return Touple((endX - startX) * ratio, (endY - startY) * ratio)
    }
}