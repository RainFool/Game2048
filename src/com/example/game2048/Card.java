package com.example.game2048;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

    private int num = 0;
    private TextView label;

    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setBackgroundColor(0x33ffffff);
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);

        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);
        setNum(0);

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }
    }

    public boolean equals(Card o) {
        return getNum() == o.getNum();
    }

    //新的卡片生成动画
    public void animationCardBorn() {
        ObjectAnimator animation = ObjectAnimator.ofFloat(this, "born", 0f, 0.99f);
        animation.setDuration(150);
        animation.start();
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                Card.this.setAlpha(cVal);
                Card.this.setScaleX(cVal);
                Card.this.setScaleY(cVal);
            }
        });
    }
    //卡片滑动动画,并没有什么卵用，弃用
    public void animationCardSlide(){
        ObjectAnimator animation = ObjectAnimator.ofFloat(this, "translationX", 0f, 360f);
        animation.setDuration(1000);
        animation.start();

    }
}
