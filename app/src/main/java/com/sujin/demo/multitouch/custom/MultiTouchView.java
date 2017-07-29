package com.sujin.demo.multitouch.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import com.sujin.demo.multitouch.constants.Circle;

public class MultiTouchView extends View {

    private Paint mPaint;
    private SparseArray<PointF> pointerList;

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        pointerList = new SparseArray<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Circle.CIRCLE_COLOR);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointerIndex = event.getActionIndex();
        int pointerID = event.getPointerId(pointerIndex);
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                PointF point = new PointF();
                point.x = event.getX(pointerIndex);
                point.y = event.getY(pointerIndex);
                pointerList.put(pointerID, point);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++) {
                    PointF point = pointerList.get(event.getPointerId(i));
                    if (point != null) {
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                pointerList.remove(pointerID);
                break;
            }
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int pointerSize = pointerList.size();
        for (int i = 0; i < pointerSize; i++) {
            PointF point = pointerList.valueAt(i);
            if(point!=null){
                mPaint.setColor(Circle.CIRCLE_COLOR);
            }
            canvas.drawCircle(point.x, point.y, Circle.CIRCLE_SIZE, mPaint);
        }
    }
}
