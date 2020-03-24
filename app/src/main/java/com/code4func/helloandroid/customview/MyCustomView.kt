package com.code4func.helloandroid.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.code4func.helloandroid.R

/**
 * TODO: document your custom view class.
 */
class MyCustomView : View {
    private val paint =  Paint(Paint.ANTI_ALIAS_FLAG)
    private val defaultColor = Color.YELLOW

    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val typeArray = context?.theme?.obtainStyledAttributes(attrs,
            R.styleable.MyCustomView, 0, 0)


        val color = typeArray?.getColor(R.styleable.MyCustomView_viewColor, defaultColor)
        if (color != null) {
            paint.color = color
        }

        typeArray?.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            measureSize(widthMeasureSpec),
            measureSize(heightMeasureSpec)
        )
    }

    private fun measureSize(measureSpec: Int) : Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val sizeInPixel = MeasureSpec.getSize(measureSpec)


        return when(mode) {
            MeasureSpec.EXACTLY -> {
                sizeInPixel
            }

            MeasureSpec.AT_MOST -> {
                sizeInPixel.coerceAtMost(50)
            }

            MeasureSpec.UNSPECIFIED -> {
                sizeInPixel.coerceAtMost(500)
            }

            else -> {
                0
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = width / 2f
        canvas?.drawCircle(width / 2f, width / 2f, radius, paint)
    }


    fun changeColor(newColor: Int) {
        paint.color = newColor
        //invalidate()
        postInvalidate()
    }
}
