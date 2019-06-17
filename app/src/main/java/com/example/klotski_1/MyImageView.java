package com.example.klotski_1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class MyImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {
    private String name;    // image的名字
    private int left;       // image距离左边框的距离
    private int top;        // image距离上边框的距离
    private int width;      // image的宽
    private int height;     // image的高
    // 手指按下时的坐标
    private int touchX;
    private int touchY;
    // 当前触摸点的坐标
    private int currentX;
    private int currentY;
    // 宽度
    final int size = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics()));
    // 画笔
    private static Paint paint;

    public MyImageView(Context context, String name, int left, int top, int width, int height) {
        super(context);
        this.name = name;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public String getMyName() {
        return name;
    }

    public int getMyLeft() {
        return left;
    }

    public int getMyTop() {
        return top;
    }

    public int getMyWidth() {
        return width;
    }

    public int getMyHeight() {
        return height;
    }

    public void setMyLeft(int left) {
        this.left = left;
    }

    public void setMyTop(int top) {
        this.top = top;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        currentX = (int) event.getRawX();
//        currentY = (int) event.getRawY();
//        int off_x = 0;
//        int off_y = 0;
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                touchX = (int) event.getRawX();
//                touchY = (int) event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                off_x = (int) event.getRawX() - touchX;
//                off_y = (int) event.getRawY() - touchY;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (Math.abs(off_x) > Math.abs(off_y)) {
//                    if (off_x > 0) {
//                        if (left > 0) {
//                            left--;
//                        }
//                    } else if (off_x < 0) {
//                        if (left < 4) {
//                            left++;
//                        }
//                    }
//                    this.setLeft(left * size);
//                } else if (Math.abs(off_x) < Math.abs(off_y)) {
//                    if (off_y > 0) {
//                        if (top < 4) {
//                            top++;
//                        }
//                    } else if (off_y < 0) {
//                        if (top > 0) {
//                            top--;
//                        }
//                    }
//                    this.setTop(top * size);
//                }
//                break;
//            default:
//                break;
//        }
        return false;
    }
}
