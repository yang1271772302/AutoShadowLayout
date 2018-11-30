package com.bobby.autolayout;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class AutoShadowLayout extends RelativeLayout implements View.OnTouchListener {

    private View shadow;
    private int COLOR_SHADOW_BLACK = Color.argb(50,0,0,0);
    private int colorShadow = COLOR_SHADOW_BLACK;
    private boolean isAuto = false;
    private boolean isPress = false;
    private boolean isClick = false;
    private OnClickListener listener;
    private boolean isCanClick = false;

    private int oldX = 0;
    private int oldY = 0;
    private int viewXStart = 0;
    private int viewXStop = 0;
    private int viewYStart = 0;
    private int viewYStop = 0;

    public AutoShadowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoShadowLayout);

        colorShadow = a.getColor(R.styleable.AutoShadowLayout_shadow_press_color, COLOR_SHADOW_BLACK);
        isAuto = a.getBoolean(R.styleable.AutoShadowLayout_shadow_auto, false);
        isPress = a.getBoolean(R.styleable.AutoShadowLayout_shadow_press, false);
        isCanClick = a.getBoolean(R.styleable.AutoShadowLayout_shadow_can_click,false);
        a.recycle();

        init(context);
    }

    private void init(Context context){
        shadow = new View(context);
        shadow.setBackgroundColor(colorShadow);
        LayoutParams rl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        shadow.setLayoutParams(rl);
//
//        if (!isPress)
            shadow.setVisibility(View.GONE);

        if (isAuto){
            setOnTouchListener(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.addView(shadow);
        if (!isPress) {
            shadow.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                viewXStart = (int)v.getX();
                viewXStop = viewXStart + v.getWidth();
                viewYStart = (int)v.getY();
                viewYStop = viewYStart + v.getHeight();
                shadow.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_MOVE:
                oldX = (int)event.getX();
                oldY = (int)event.getY();
                if(oldX > 0 && oldX < viewXStop - viewXStart && oldY  > 0 && oldY < viewYStop - viewYStart){
                    shadow.setVisibility(View.VISIBLE);
                }else {
                    shadow.setVisibility(View.GONE);
                    isClick = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                shadow.setVisibility(View.GONE);
                getParent().requestDisallowInterceptTouchEvent(false);
                if (isCanClick)
                    if (isClick){
                        listener.onClick(v);
                    }
                isClick = false;
                break;
        }
        return true;
    }

    public void setPress(boolean press) {
        if (press) {
            shadow.setVisibility(View.VISIBLE);
        } else {
            shadow.setVisibility(View.GONE);
        }
        invalidate();
    }

    public interface OnClickListener{
        void onClick(View view);
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

}
