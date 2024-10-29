package com.android.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class UIEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatEditText(context, attrs) {
    private var cornersHelper: CornersHelper

    init {
        setWillNotDraw(false)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UIEditText, 0, 0)
        val radius = typedArray.getDimension(R.styleable.UIEditText_android_radius, 0f)
        val topLeft =
            typedArray.getDimension(R.styleable.UIEditText_android_topLeftRadius, 0f)
        val topRight =
            typedArray.getDimension(R.styleable.UIEditText_android_topRightRadius, 0f)
        val bottomLeft =
            typedArray.getDimension(R.styleable.UIEditText_android_bottomLeftRadius, 0f)
        val bottomRight =
            typedArray.getDimension(R.styleable.UIEditText_android_bottomRightRadius, 0f)
        typedArray.recycle()
        val cornersModel = CornersModel(radius, topLeft, topRight, bottomLeft, bottomRight)
        cornersHelper = CornersHelper(cornersModel)
    }

    override fun draw(canvas: Canvas) {
        cornersHelper.draw(Pair(width.toFloat(), height.toFloat()), canvas)
        super.draw(canvas)
    }

    fun setCorners(cornersModel: CornersModel) {
        cornersHelper.setCornersModel(cornersModel)
        invalidate()
    }
}