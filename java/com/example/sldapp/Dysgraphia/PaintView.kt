package com.example.sldapp.Dysgraphia

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.sldapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.abs

class PaintView @JvmOverloads constructor(context: Context, attrs : AttributeSet ?= null, defStyleAttr : Int = 0) : View (context, attrs, defStyleAttr) {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var database : DatabaseReference

    private var paintBrush : Paint ?= null
    private var pathList = ArrayList<PaintPath>()
    private var mPath : Path ?= null
    private var mX : Float ?= null
    private var mY : Float ?= null
    private var touchTolerance : Float = 4f

init{
    fireBaseInit()
    paintBrush = Paint()
    paintBrush!!.color = ResourcesCompat.getColor(resources, R.color.brushColor, null)
    paintBrush!!.isAntiAlias = true
    paintBrush!!.style = Paint.Style.STROKE
    paintBrush!!.strokeWidth = 20f

}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
              touchStart(x,y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE ->{
               touchMove(x,y)
                invalidate()
            }
            MotionEvent.ACTION_UP ->{
                touchUp()
                invalidate()
            }
            else -> {
            }
        }
        invalidate()
        return true
    }

    private fun touchUp() {
        mPath!!.lineTo(mX!!,mY!!)
        val pathID = database.push().key
        if (pathID != null) {
            database.child(pathID).child("x").setValue(mX!!.toInt())
            database.child(pathID).child("y").setValue(mY!!.toInt())
        }
    }

    private fun touchMove(x: Float, y: Float) {
        val dX : Float = abs(x - mX!!)
        val dY : Float = abs(y - mY!!)
        if(dX >= touchTolerance || dY >= touchTolerance){
            mPath!!.quadTo(mX!!,mY!!,(x+mX!!)/2, (y+mY!!)/2)
            mX = x
            mY = y
        }
    }

    private fun touchStart(x: Float, y: Float) {
            mPath = Path()
            val paintPath = PaintPath(mPath!!)
            pathList.add(paintPath)
            mPath!!.reset()
            mPath!!.moveTo(x,y)
            mX = x
            mY = y
    }


    override fun onDraw(canvas: Canvas) {
        if(pathList.size > 0){
            for(path in pathList){
                canvas.drawPath(path.path, paintBrush!!)
            }
        }
    }

    fun onUndo(){
        if(pathList.size > 0){
            pathList.removeAt(pathList.size - 1)
            invalidate()
        }
    }

    fun onReset(){
        if(pathList.size > 0) {
            mPath!!.reset()
            pathList.clear()
            invalidate()
        }
    }

    private fun fireBaseInit(){
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            val user = firebaseAuth.currentUser!!.uid
            database = FirebaseDatabase.getInstance("https://sld-project-default-rtdb.europe-west1.firebasedatabase.app//").getReference("User").child(user).child("Dysgraphia")
        }
    }
}