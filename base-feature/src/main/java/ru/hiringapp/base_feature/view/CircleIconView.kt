package ru.hiringapp.base_feature.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import ru.hiringapp.base_feature.R
import kotlin.math.min

class CircleIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var circleColor: Int = Color.BLUE
    private var iconDrawable: Drawable? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CircleIconView)
            circleColor = typedArray.getColor(R.styleable.CircleIconView_circleColor, Color.BLUE)
            iconDrawable = typedArray.getDrawable(R.styleable.CircleIconView_iconDrawable)
            typedArray.recycle()
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val radius = min(width, height) / 2f
        val cx = width / 2f
        val cy = height / 2f

        paint.color = circleColor
        canvas.drawCircle(cx, cy, radius, paint)

        iconDrawable?.let {
            val iconSize = (radius * 2 - 8.dpToPx()).toInt()
            val left = (cx - iconSize / 2).toInt()
            val top = (cy - iconSize / 2).toInt()
            val right = (cx + iconSize / 2).toInt()
            val bottom = (cy + iconSize / 2).toInt()

            it.setBounds(left, top, right, bottom)
            it.draw(canvas)
        }
    }

    fun setCircleColor(color: Int) {
        circleColor = color
        invalidate()
    }

    fun setIconDrawable(drawable: Drawable?) {
        iconDrawable = drawable
        invalidate()
    }

    private fun Int.dpToPx(): Float {
        return this * resources.displayMetrics.density
    }
}
