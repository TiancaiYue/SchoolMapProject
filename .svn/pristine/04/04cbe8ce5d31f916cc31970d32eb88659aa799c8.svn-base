package com.keyuan.schoolmap.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class LetterView extends View {

    OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    //    String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
//            "U", "V", "W", "X", "Y", "Z" };
    private List<String> letterList;
    private int choose = -1;
    private Paint paint = new Paint();
    private boolean showBkg = false;

    public LetterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LetterView(Context context) {
        super(context);
        init();
    }

    private void init() {
        letterList = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (letterList.size() == 0)
            return;
        if (showBkg) {
            // 设置字母条选中后的背景颜色
            // canvas.drawColor(Color.parseColor("#40000000"));
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / letterList.size();
        //int singleHeight = height / 27;  // 单个高度为屏幕高度除（26个字母+“#”）的高度
        for (int i = 0; i < letterList.size(); i++) {
            paint.setColor(Color.parseColor("#279CEF"));
            paint.setTextSize(28);
            // 粗体
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(Color.parseColor("#000000"));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(letterList.get(i)) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(letterList.get(i), xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * letterList.size()); // 获取到点击的具体位置
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                showBkg = true;
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < letterList.size()) {
                        listener.onTouchingLetterChanged(letterList.get(c));
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != c && listener != null) {
                    if (c >= 0 && c < letterList.size()) {
                        listener.onTouchingLetterChanged(letterList.get(c));
                        choose = c;
                        invalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                choose = -1;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

    public void setLetter(List<String> letterList) {
        this.letterList = letterList;
        invalidate();
    }
}
