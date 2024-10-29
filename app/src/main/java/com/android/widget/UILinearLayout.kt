package com.android.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout

class UILinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private var cornersHelper: CornersHelper

    init {
        setWillNotDraw(false)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.UILinearLayout, 0, 0)
        val radius = typedArray.getDimension(R.styleable.UILinearLayout_android_radius, 0f)
        val topLeft =
            typedArray.getDimension(R.styleable.UILinearLayout_android_topLeftRadius, 0f)
        val topRight =
            typedArray.getDimension(R.styleable.UILinearLayout_android_topRightRadius, 0f)
        val bottomLeft =
            typedArray.getDimension(R.styleable.UILinearLayout_android_bottomLeftRadius, 0f)
        val bottomRight =
            typedArray.getDimension(R.styleable.UILinearLayout_android_bottomRightRadius, 0f)
        typedArray.recycle()
        val cornersModel = CornersModel(radius, topLeft, topRight, bottomLeft, bottomRight)
        cornersHelper = CornersHelper(cornersModel)
    }

    override fun draw(canvas: Canvas) {
        cornersHelper.draw(Pair(width.toFloat(), height.toFloat()), canvas)
        super.draw(canvas)
    }
}